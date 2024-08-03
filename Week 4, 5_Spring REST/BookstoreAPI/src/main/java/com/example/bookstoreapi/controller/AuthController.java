package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.security.JwtUtil;
import com.example.bookstoreapi.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(((UserDetails) authRequest).getUsername(), ((UserDetails) authRequest).getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(((UserDetails) authRequest).getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}

class AuthRequest {
    private String username;
    private String password;

    // Getters and Setters
}
