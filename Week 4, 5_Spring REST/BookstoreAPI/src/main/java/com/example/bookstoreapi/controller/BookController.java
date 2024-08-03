package com.example.bookstoreapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.resource.BookResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Validated
@Tag(name = "Books", description = "Endpoints for managing books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookController(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    @ApiResponse(responseCode = "200", description = "List of books retrieved successfully")
    public ResponseEntity<CollectionModel<BookResource>> getAllBooks() {
        List<BookResource> bookResources = bookRepository.findAll().stream()
                .map(book -> new BookResource(bookMapper.bookToBookDTO(book)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(bookResources));
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Create a new book record")
    @ApiResponse(responseCode = "201", description = "Book created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<BookResource> addBook(@Validated @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book savedBook = bookRepository.save(book);

        BookResource bookResource = new BookResource(bookMapper.bookToBookDTO(savedBook));
        return new ResponseEntity<>(bookResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieve a book by its ID")
    @ApiResponse(responseCode = "200", description = "Book retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<BookResource> getBookById(@PathVariable int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        BookResource bookResource = new BookResource(bookMapper.bookToBookDTO(book));
        return ResponseEntity.ok(bookResource);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update the details of an existing book")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<BookResource> updateBook(@PathVariable int id, @Validated @RequestBody BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setIsbn(bookDTO.getIsbn());

        Book updatedBook = bookRepository.save(existingBook);

        BookResource bookResource = new BookResource(bookMapper.bookToBookDTO(updatedBook));
        return ResponseEntity.ok(bookResource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Remove a book from the system")
    @ApiResponse(responseCode = "204", description = "Book deleted successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
