package com.anhbui.bookstore.repository;

import com.anhbui.bookstore.entity.Order;
import com.anhbui.bookstore.entity.OrderDetail;
import com.anhbui.bookstore.entity.composite_key.OrderDetailId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findByOrder(Order order);
}
