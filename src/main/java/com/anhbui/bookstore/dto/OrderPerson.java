package com.anhbui.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPerson {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
}
