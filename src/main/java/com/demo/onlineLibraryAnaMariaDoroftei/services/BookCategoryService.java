package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidBookCategoryIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidUserIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    public BookCategory saveBookCategory(BookCategory category){
        return bookCategoryRepository.save(category);
    }

    public BookCategory getBookCategory(Long bookCategoryId) {
        return bookCategoryRepository.findById(bookCategoryId)
                .orElseThrow(InvalidBookCategoryIdException::new);
    }

    public List<BookCategory> getBookCategories(){
        List<BookCategory> bookCategories = new ArrayList<>();
        Iterable<BookCategory> bookCategoryFromDB = bookCategoryRepository.findAll();
        for (BookCategory bookCategory : bookCategoryFromDB) {
            bookCategories.add(bookCategory);
        }
        return bookCategories;
    }

    public void deleteBookCategories(Long bookCategoryId) {
        BookCategory bookCategoryToDelete = getBookCategory(bookCategoryId);
        bookCategoryRepository.delete(bookCategoryToDelete);
    }

}
