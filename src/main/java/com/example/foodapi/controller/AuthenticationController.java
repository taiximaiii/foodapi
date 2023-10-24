package com.example.foodapi.controller;

import com.example.foodapi.model.Customer;
import com.example.foodapi.service.AuthenticationService;
import com.example.foodapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer){
        if(customerService.findByEmail(customer.getEmail()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Customer customer) {
        return new ResponseEntity<>(authenticationService.LoginAndReturnJWT(customer), HttpStatus.OK);
    }
}
