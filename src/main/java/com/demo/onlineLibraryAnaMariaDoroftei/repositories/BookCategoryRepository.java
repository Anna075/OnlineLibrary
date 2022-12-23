package com.demo.onlineLibraryAnaMariaDoroftei.repositories;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookCategoryRepository extends CrudRepository<BookCategory, Long> {
    Optional<BookCategory> findByName(String name);
}
