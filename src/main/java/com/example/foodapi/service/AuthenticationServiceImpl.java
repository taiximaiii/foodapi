package com.example.foodapi.service;

import com.example.foodapi.model.Customer;
import com.example.foodapi.security.UserPrincipal;
import com.example.foodapi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @Override
    public Customer LoginAndReturnJWT(Customer signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword())
        );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String jwt = jwtProvider.generateToken(userPrincipal);
        Customer registerCustomer = userPrincipal.getCustomer();
        registerCustomer.setToken(jwt);
        return registerCustomer;
    }

}
