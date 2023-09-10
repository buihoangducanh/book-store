package com.anhbui.bookstore.service;

import com.anhbui.bookstore.entity.Order;
import com.anhbui.bookstore.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetailByOrder(Order order);
}
