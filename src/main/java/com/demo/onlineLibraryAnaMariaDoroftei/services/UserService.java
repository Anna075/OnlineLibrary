package com.demo.onlineLibraryAnaMariaDoroftei.services;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidUserIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Long userId) throws InvalidUserIdException {
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

    public void deleteUser(Long userId) throws InvalidUserIdException {
        User userToDelete = getUser(userId);
        userRepository.delete(userToDelete);
    }

}
