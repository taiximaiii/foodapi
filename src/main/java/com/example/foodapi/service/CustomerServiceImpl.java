package com.example.foodapi.service;

import com.example.foodapi.model.Customer;
import com.example.foodapi.model.Payment;
import com.example.foodapi.repository.CustomerRepository;
import com.example.foodapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements  CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Customer saveCustomer(Customer customer){
        if (customer.getBankNumber() != null){
            customer.addPayment(paymentRepository.findPaymentByName("banking"));
        }
        customer.addPayment(paymentRepository.findPaymentByName("cash"));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
    @Override
    public Optional<Customer> findByEmail(String email){
        return customerRepository.findByEmail(email);
    }
    @Override
    public Customer updateCustomer(Customer customer,Long id) {

        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAge(customer.getAge());
            existingCustomer.setFullName(customer.getFullName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAddress(customer.getAddress());

            return customerRepository.save(existingCustomer);
        }
        return null;
    }
}
