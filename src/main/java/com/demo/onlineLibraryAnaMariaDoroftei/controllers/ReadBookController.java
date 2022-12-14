package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.ImmutableReadBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidReadBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.services.ReadBookService;
import com.demo.onlineLibraryAnaMariaDoroftei.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/readBooks")
@RequiredArgsConstructor
public class ReadBookController {
    private final ReadBookService readBookService;
    private final UserService userService;

    @PostMapping()
    public ReadBook saveReadBook(@RequestBody ReadBook readBook){
        return readBookService.saveReadBook(readBook);
    }

    @GetMapping()
    public List<ReadBook> getReadBooks(){
        return readBookService.getReadBooks();
    }

    @GetMapping("/{readBookId}")
    public ReadBook getReadBook(@PathVariable Long readBookId) {
        return readBookService.getReadBook(readBookId);
    }

    @DeleteMapping("/{readBookId}")
    public void deleteReadBook(@PathVariable Long readBookId) {
        readBookService.deleteReadBook(readBookId);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(ImmutableReadBookIdException e){
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        return new ResponseEntity<>("READ BOOK IS IMMUTABLE", headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(InvalidReadBookIdException e){
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        return new ResponseEntity<>("READ BOOK NOT FOUNT", headers, HttpStatus.NOT_FOUND);
    }

}
