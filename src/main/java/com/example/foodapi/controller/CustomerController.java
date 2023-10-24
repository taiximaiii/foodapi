package com.example.foodapi.controller;

import com.example.foodapi.model.Customer;
import com.example.foodapi.security.UserPrincipal;
import com.example.foodapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/profile")
    public ResponseEntity<?> customerProfile(Authentication authentication){
        String email = authentication.getName();
        Optional<Customer> customer = customerService.findByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long id = userPrincipal.getId();
        Customer updatedCustomer = customerService.updateCustomer(customer,id);

        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
