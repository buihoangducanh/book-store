package com.anhbui.bookstore.service;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
