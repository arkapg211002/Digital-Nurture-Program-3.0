package com.example.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstoreapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
