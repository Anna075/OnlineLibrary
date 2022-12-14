package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidBookCategoryIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.services.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/bookCategories")
@RequiredArgsConstructor
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;

    @PostMapping()
    public BookCategory saveBookCategory(@RequestBody BookCategory bookCategory){
        return bookCategoryService.saveBookCategory(bookCategory);
    }

    @GetMapping()
    public List<BookCategory> getBookCategories(){
        return bookCategoryService.getBookCategories();
    }

    @GetMapping("/{bookCategoryId}")
    public BookCategory getBookCategory(@PathVariable Long bookCategoryId) {
        return bookCategoryService.getBookCategory(bookCategoryId);
    }

    @DeleteMapping("/{bookCategoryId}")
    public void deleteBookCategory(@PathVariable Long bookCategoryId) {
        bookCategoryService.deleteBookCategories(bookCategoryId);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(InvalidBookCategoryIdException e){
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        return new ResponseEntity<>("BOOK CATEGORY NOT FOUND", headers, HttpStatus.NOT_FOUND);
    }
}
