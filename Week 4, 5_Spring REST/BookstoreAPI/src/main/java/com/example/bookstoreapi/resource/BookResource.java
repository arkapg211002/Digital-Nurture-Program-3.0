package com.example.bookstoreapi.resource;

import com.example.bookstoreapi.controller.BookController;
import com.example.bookstoreapi.dto.BookDTO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class BookResource extends RepresentationModel<BookResource> {
    private BookDTO bookDTO;

    public BookResource(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
        addLinks();
    }

    private void addLinks() {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel();
        add(selfLink);
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");
        add(allBooksLink);
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}

