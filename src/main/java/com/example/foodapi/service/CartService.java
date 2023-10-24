package com.example.foodapi.service;

import com.example.foodapi.dto.CartItemDto;
import com.example.foodapi.model.Item;

import java.util.List;

public interface CartService {
    void addItemToCart(Long customerId, Long itemId);
    void removeItemFromCart(Long customerId, Long itemId);
    List<CartItemDto> getCartItemsWithDetails(Long customerId);
}
