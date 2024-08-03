package com.example.bookstoreapi.resource;

import com.example.bookstoreapi.controller.CustomerController;
import com.example.bookstoreapi.dto.CustomerDTO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class CustomerResource extends RepresentationModel<CustomerResource> {
    private CustomerDTO customerDTO;

    public CustomerResource(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
        addLinks();
    }

    private void addLinks() {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customerDTO.getId())).withSelfRel();
        add(selfLink);
        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers");
        add(allCustomersLink);
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}

