package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidUserIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.ReadBookRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final ReadBookRepository readBookRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(InvalidUserIdException::new);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        Iterable<User> usersFromDB = userRepository.findAll();
        for (User user : usersFromDB) {
            users.add(user);
        }
        return users;
    }

    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }

    public List<ReadBook> getReadBooksByUserId(Long userId) {
        return getUser(userId).getReadBooks();
    }
}