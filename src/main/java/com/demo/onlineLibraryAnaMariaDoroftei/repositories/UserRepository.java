package com.demo.onlineLibraryAnaMariaDoroftei.repositories;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
