package com.demo.onlineLibraryAnaMariaDoroftei.repositories;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import org.springframework.data.repository.CrudRepository;

public interface BookCategoryRepository extends CrudRepository<BookCategory, Long> {
    BookCategory findByName(String name);
}
