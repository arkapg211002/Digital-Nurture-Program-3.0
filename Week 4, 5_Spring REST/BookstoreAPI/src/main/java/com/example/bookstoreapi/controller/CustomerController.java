package com.example.bookstoreapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.dto.CustomerDTO;
import com.example.bookstoreapi.mapper.CustomerMapper;
import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import com.example.bookstoreapi.resource.CustomerResource;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CustomerResource>> getAllCustomers() {
        List<CustomerResource> customerResources = customerRepository.findAll().stream()
                .map(customer -> new CustomerResource(customerMapper.customerToCustomerDTO(customer)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(customerResources));
    }

    @PostMapping
    public ResponseEntity<CustomerResource> addCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerResource customerResource = new CustomerResource(customerMapper.customerToCustomerDTO(savedCustomer));
        return new ResponseEntity<>(customerResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResource> getCustomerById(@PathVariable int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        CustomerResource customerResource = new CustomerResource(customerMapper.customerToCustomerDTO(customer));
        return ResponseEntity.ok(customerResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResource> updateCustomer(@PathVariable int id, @Validated @RequestBody CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
     
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        CustomerResource customerResource = new CustomerResource(customerMapper.customerToCustomerDTO(updatedCustomer));
        return ResponseEntity.ok(customerResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }

        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}