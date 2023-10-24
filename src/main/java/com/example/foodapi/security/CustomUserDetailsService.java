package com.example.foodapi.security;

import com.example.foodapi.model.Customer;
import com.example.foodapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));

        return UserPrincipal.builder()
                .customer(customer)
                .id(customer.getCustomerId())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }
}