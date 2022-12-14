package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(InvalidBookIdException::new);
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        Iterable<Book> booksFromDB = bookRepository.findAll();
        for (Book book : booksFromDB) {
            books.add(book);
        }
        return books;
    }

    public void deleteBook(Long bookId){
        Book bookToDelete = getBook(bookId);
        bookRepository.delete(bookToDelete);
    }

}
