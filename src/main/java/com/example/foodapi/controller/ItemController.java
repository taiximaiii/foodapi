package com.example.foodapi.controller;

import com.example.foodapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/food")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/detail/{itemId}")
    public  ResponseEntity<?> getDetail(@PathVariable Long itemId){
        return new ResponseEntity<>(itemService.findById(itemId),HttpStatus.OK);
    }
}
