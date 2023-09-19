package com.anhbui.bookstore.service.impl;

import com.anhbui.bookstore.constant.OrderStatus;
import com.anhbui.bookstore.constant.PaymentMethod;
import com.anhbui.bookstore.dto.CartDTO;
import com.anhbui.bookstore.dto.CartItemDTO;
import com.anhbui.bookstore.dto.OrderPerson;
import com.anhbui.bookstore.entity.Book;
import com.anhbui.bookstore.entity.Order;
import com.anhbui.bookstore.entity.OrderDetail;
import com.anhbui.bookstore.entity.User;
import com.anhbui.bookstore.repository.BookRepository;
import com.anhbui.bookstore.repository.OrderDetailRepository;
import com.anhbui.bookstore.repository.OrderRepository;
import com.anhbui.bookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private BookRepository bookRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getAllOrdersOnPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public void setProcessingOrder(Order order) {
        order.setStatus(OrderStatus.PROCESSING);
        orderRepository.save(order);
    }

    @Override
    public void setDeliveringOrder(Order order) {
        order.setStatus(OrderStatus.DELIVERING);
        orderRepository.save(order);
    }

    @Override
    public void setReceivedToOrder(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        Order savedOrder = orderRepository.save(order);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(savedOrder);
        for (OrderDetail od :
                orderDetails) {
            Book book = od.getBook();
            int oldCount = book.getBuyCount() != null ? book.getBuyCount() : 0;
            book.setBuyCount(oldCount + od.getQuantity());
            bookRepository.save(book);
        }
    }

    @Override
    public List<Order> getTop10orders() {
        return orderRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return orderRepository.sumTotalPrice();
    }

    @Override
    public Long countOrder() {
        return orderRepository.count();
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) {
        return orderRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order createOrder(CartDTO cart, User user, OrderPerson orderPerson) {
        Order order = new Order();
        order.setReciever(orderPerson.getFullName());
        order.setStatus(OrderStatus.PENDING);
        order.setEmailAddress(orderPerson.getEmail());
        order.setShippingAddress(orderPerson.getAddress());
        order.setPhoneNumber(orderPerson.getPhoneNumber());
        order.setTotalPrice(cart.calculateTotalAmount());
        order.setPaymentMethod(PaymentMethod.COD);

        // Thêm các chi tiết đơn hàng từ giỏ hàng
        List<CartItemDTO> cartItems = cart.getCartItems();
        for (CartItemDTO cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            Book book = bookRepository.findById(cartItem.getBookId()).orElse(null);
            orderDetail.setBook(book);
            orderDetail.setQuantity(cartItem.getQuantity());
            assert book != null;
            orderDetail.setPrice(book.getSalePrice());
            order.addOrderDetail(orderDetail);
        }

        // Set thông tin người dùng và thời gian
        order.setUser(user);
        order.setCreatedAt(new Date());
        // Lưu đơn đặt hàng vào cơ sở dữ liệu
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void cancelOrder(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    @Override
    public Page<Order> getOrdersByStatus(String status, Pageable pageable) {
        return orderRepository.findByStatus(status, pageable);
    }


}
