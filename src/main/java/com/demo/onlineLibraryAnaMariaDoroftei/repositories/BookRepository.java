package com.demo.onlineLibraryAnaMariaDoroftei.repositories;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
