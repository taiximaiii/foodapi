package com.example.foodapi.repository;

import com.example.foodapi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findPaymentByName(String name);
}
