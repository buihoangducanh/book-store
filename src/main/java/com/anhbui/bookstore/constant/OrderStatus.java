package com.anhbui.bookstore.constant;

public interface OrderStatus {
    String PENDING = "PENDING";         // Đang chờ xử lý
    String PROCESSING = "PROCESSING";     // Đang xử lý
    String DELIVERING = "DELIVERING";        // Đang giao hàng
    String DELIVERED = "DELIVERED";      // Đã giao thành công
    String CANCELLED = "CANCELLED";       // Đã hủy
}

