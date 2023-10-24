package com.example.foodapi.controller;

import com.example.foodapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
}
