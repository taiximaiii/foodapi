package com.example.foodapi.repository;

import com.example.foodapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByCustomerCustomerId(Long customerId);
}
