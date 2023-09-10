package com.anhbui.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemDTO {
    private Long bookId;
    private String coverImage;
    private String title;
    private Double price;
    private Integer quantity;

    public double getSubtotal() {
        return price * quantity;
    }
}
