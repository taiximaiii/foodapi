package com.example.foodapi.controller;

import com.example.foodapi.dto.OrderDetailDto;
import com.example.foodapi.security.UserPrincipal;
import com.example.foodapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@AuthenticationPrincipal UserPrincipal userPrincipal){
        orderService.placeOrder(userPrincipal.getId());
        return ResponseEntity.ok("Order Success");
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrder(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return new ResponseEntity<>(orderService.getAllOrder(userPrincipal.getId()), HttpStatus.OK);
    }
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<OrderDetailDto> getOrderDetails(@PathVariable Long orderId) {
        OrderDetailDto orderDetails = orderService.getOrderDetailById(orderId);
        if (orderDetails != null) {
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
