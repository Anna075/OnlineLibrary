package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    @PostMapping
    public void addBook(@RequestBody Model model){

    }



}
