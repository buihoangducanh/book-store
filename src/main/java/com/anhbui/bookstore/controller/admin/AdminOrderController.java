package com.anhbui.bookstore.controller.admin;

import com.anhbui.bookstore.controller.common.BaseController;
import com.anhbui.bookstore.entity.Order;
import com.anhbui.bookstore.entity.OrderDetail;
import com.anhbui.bookstore.service.OrderDetailService;
import com.anhbui.bookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/orders_management")
@AllArgsConstructor
public class AdminOrderController extends BaseController {

    private final OrderService orderService;
    private OrderDetailService orderDetailService;


    @GetMapping
    public String getAllOrders(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {

        int pageSize = 5;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());

        Page<Order> orderPage;
        if (status != null && !status.isEmpty()) {
            orderPage = orderService.getOrdersByStatus(status, pageable);
        } else {
            orderPage = orderService.getAllOrdersOnPage(pageable);
        }

        model.addAttribute("orderPage", orderPage);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("currentPage", orderPage.getNumber());
        model.addAttribute("totalPages", orderPage.getTotalPages());

        return "admin/order";
    }
    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id) {

        Order order = orderService.getOrderById(id);
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetailByOrder(order);
        model.addAttribute("order", order);

        model.addAttribute("ordersDetails", orderDetails);

        return "admin/order_detail";
    }

    @GetMapping("/details/process/{id}")
    public String process(@PathVariable Long id) {

        Order order = orderService.getOrderById(id);
        orderService.setProcessingOrder(order);

        return "redirect:/admin/orders_management/details/"+id;
    }
    @GetMapping("/details/deliver/{id}")
    public String deliver( @PathVariable Long id) {

        Order order = orderService.getOrderById(id);
        orderService.setDeliveringOrder(order);

        return "redirect:/admin/orders_management/details/"+id;
    }
    @GetMapping("/details/cancel/{id}")
    public String cancel( @PathVariable Long id) {

        Order order = orderService.getOrderById(id);
        orderService.cancelOrder(order);

        return "redirect:/admin/orders_management/details/"+id;
    }
}
