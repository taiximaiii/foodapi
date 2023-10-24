package com.example.foodapi.service;


import com.example.foodapi.model.Customer;

public interface AuthenticationService {
    Customer LoginAndReturnJWT(Customer signInRequest);

}
