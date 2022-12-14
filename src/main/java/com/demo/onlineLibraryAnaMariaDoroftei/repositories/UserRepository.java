package com.demo.onlineLibraryAnaMariaDoroftei.repositories;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
