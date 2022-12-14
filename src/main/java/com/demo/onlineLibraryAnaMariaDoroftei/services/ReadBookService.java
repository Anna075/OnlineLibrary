package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.ImmutableReadBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidReadBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.ReadBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadBookService {

    private final ReadBookRepository readBookRepository;

    public ReadBook saveReadBook(ReadBook readBook){
        if(readBook.getId() == null){
            return readBookRepository.save(readBook);
        } else {
            throw new ImmutableReadBookIdException();
        }
    }

    public ReadBook getReadBook(Long readBookId) {
        return readBookRepository.findById(readBookId)
                .orElseThrow(InvalidReadBookIdException::new);
    }

    public List<ReadBook> getReadBooks(){
        List<ReadBook> readBooks = new ArrayList<>();
        Iterable<ReadBook> readBooksFromDB = readBookRepository.findAll();
        for (ReadBook readBook : readBooksFromDB) {
            readBooks.add(readBook);
        }
        return readBooks;
    }

    public void deleteReadBook(Long readBookId) {
        ReadBook readBookToDelete = getReadBook(readBookId);
        readBookRepository.delete(readBookToDelete);
    }
}
