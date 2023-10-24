package com.example.foodapi.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private String itemName;
    private double totalPrice;
    private int quantity;
}
