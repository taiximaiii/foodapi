package com.example.foodapi.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailDto {
    private String orderStatus;
    private Date orderDate;
    private List<OrderItemDto> orderItems;
}






