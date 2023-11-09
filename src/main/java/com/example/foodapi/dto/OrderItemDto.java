package com.example.foodapi.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private String itemName;
    private String imageUrl;
    private double totalPrice;
    private int quantity;
}
