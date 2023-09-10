package com.anhbui.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartDTO {
    private List<CartItemDTO> cartItems = new ArrayList<>();

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0.0;
        for (CartItemDTO cartItem : cartItems) {
            totalAmount += cartItem.getSubtotal();
        }
        return totalAmount;
    }
}
