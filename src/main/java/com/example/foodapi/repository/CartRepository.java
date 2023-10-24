package com.example.foodapi.repository;

import com.example.foodapi.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCustomerCustomerId(Long id);
}
