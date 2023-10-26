package com.example.foodapi.service;

import com.example.foodapi.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> getAllItems();
    Item findById(Long itemId);
}
