package com.example.foodapi.service;

import com.example.foodapi.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Optional<Customer> findByEmail(String email);
    Customer updateCustomer(Customer customer,Long id);
}
