package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.exceptions.InvalidUserIdException;
import com.demo.onlineLibraryAnaMariaDoroftei.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user){
        if(user.getId() != null){
            return saveUser(user);
        } else {
            throw new InvalidUserIdException();
        }
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws InvalidUserIdException{
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/readBooks")
    public List<ReadBook> getReadBooksByUserId(@PathVariable Long userId){
        return userService.getReadBooksByUserId(userId);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(InvalidUserIdException e){
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        return new ResponseEntity<>("USER NOT FOUND", headers, HttpStatus.NOT_FOUND);
    }


}
