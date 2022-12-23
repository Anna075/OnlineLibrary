package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidBookCategoryNameException;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @GetMapping()
    public List<Book> getBooks(@RequestParam(required = false) String categoryName){
        return bookService.getBooks(categoryName);
    }

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable Long bookId) throws InvalidBookIdException {
        return bookService.getBook(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(InvalidBookIdException e){
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        return new ResponseEntity<>("BOOK NOT FOUND", headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(InvalidBookCategoryNameException e){
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        return new ResponseEntity<>("BOOK CATEGORY NOT FOUND", headers, HttpStatus.NOT_FOUND);
    }
}
