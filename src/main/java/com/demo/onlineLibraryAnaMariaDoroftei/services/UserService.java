package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidUserIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

//    public User saveUser(User user){
//        userRepository.save(user);
//        return user;
//    }

    public void addUser(User user){
        userRepository.save(user);
    }

        public void updateUser(Long userId) {
        User user = getUser(userId);
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setRoles(user.getRoles());
        user.setSurname(user.getSurname());
        user.setFirstname(user.getFirstname());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setJoinDate(user.getJoinDate());
        userRepository.save(user);
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
        userRepository.deleteById(userId);
    }

    public Set<ReadBook> getReadBooksByUserId(Long userId) {
        return getUser(userId).getReadBooks();
    }


}
