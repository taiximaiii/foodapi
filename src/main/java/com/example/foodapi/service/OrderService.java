package com.example.foodapi.service;

import com.example.foodapi.dto.OrderDetailDto;
import com.example.foodapi.dto.OrderItemDto;
import com.example.foodapi.model.Order;

import java.util.List;

public interface OrderService {
    void placeOrder(Long customerId);
    List<Order> getAllOrder(Long customerId);
    OrderDetailDto getOrderDetailById(Long orderId);
    void confirmPayment(Long orderId);
}
