package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidBookIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidUserIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    private void verifyBookId(Long bookId) throws InvalidBookIdException {
        if(bookId == null){
            throw new InvalidBookIdException();
        }
    }

//    public BookCategory getBookCategoryByName(String categoryName) {
//
//        BookCategory category = null;
//
//        try {
//            category = (BookCategory) entityManager.createQuery("from BookCategory c where c.name = :category")
//                    .setParameter("category", categoryName)
//                    .getSingleResult();
//        } catch (NoResultException nre) {
//
//        }
//
//
//        return category;
//    }
//
//    @Transactional
//    public boolean isBookInLibrary(String googleId) {
//        return getBookByGoogleId(googleId) != null;
//    }
//
//    public void saveBook(Model model){
//
//    }


}
