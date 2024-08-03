package com.example.bookstoreapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SecurityConfig.class)
public class TestConfig {
    // Any specific beans or configurations for testing
}
