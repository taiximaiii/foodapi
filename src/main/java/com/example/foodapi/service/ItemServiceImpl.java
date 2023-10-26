package com.example.foodapi.service;

import com.example.foodapi.model.Item;
import com.example.foodapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public Item findById(Long itemId){
        return itemRepository.findById(itemId).orElse(null);
    }
}
