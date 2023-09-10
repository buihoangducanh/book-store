package com.anhbui.bookstore.service.impl;

import com.anhbui.bookstore.entity.Order;
import com.anhbui.bookstore.entity.OrderDetail;
import com.anhbui.bookstore.repository.OrderDetailRepository;
import com.anhbui.bookstore.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> getAllOrderDetailByOrder(Order order) {
        return orderDetailRepository.findByOrder(order);
    }
}
