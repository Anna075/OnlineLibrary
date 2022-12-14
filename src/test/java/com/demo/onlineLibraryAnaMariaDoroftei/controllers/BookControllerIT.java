package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.demo.onlineLibraryAnaMariaDoroftei.utils.Utils.toJson;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
class BookControllerIT {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookController bookController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Nested
    @DisplayName("saveBook()")
    class SaveBook{
        @Test
        public void shouldReturnSavedBook() throws Exception {
            Book book = new Book();

            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(book))
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", greaterThanOrEqualTo(1))
            ) .andDo(print());
        }

        @Test
        public void shouldUpdateExistingBook() throws Exception{
            Book existingBook = new Book();
            existingBook.setLanguage("Romanian");
            existingBook = bookRepository.save(existingBook);
            Long id = existingBook.getId();

            existingBook.setLanguage("English");


            mockMvc.perform(post("/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(existingBook))
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", equalTo(id), Long.class),
                    jsonPath("$.language", equalTo(existingBook.getLanguage()))
            ) .andDo(print());
        }
    }

    @Nested
    @DisplayName("deleteBook()")
    class DeleteBook{

        @Test
        public void shouldDeleteExistingBook() throws Exception {
            Book existingBook = new Book();
            existingBook = bookRepository.save(existingBook);
            Long id = existingBook.getId();

            mockMvc.perform(delete("/books/" + id))
                    .andExpectAll(
                            status().isOk()
                    );
        }

        @Test
        public void shouldReturnNotFound() throws Exception{
            Long id = 123123123123123L;

            mockMvc.perform(delete("/books/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getBook")
    class GetBook{

        @Test
        public void shouldReturnBook() throws Exception{
            Book existingBook = new Book();
            existingBook = bookRepository.save(existingBook);
            Long id = existingBook.getId();

            mockMvc.perform(get("/books/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", equalTo(id), Long.class)
            ) .andDo(print());
        }

        @Test
        public void shouldReturnNotFound() throws Exception{
            Long id = 123123123123L;

            mockMvc.perform(delete("/books/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getBooks")
    class GetBooks{

        @Test
        public void shouldReturnBooks() throws Exception{
            bookRepository.save(new Book());
            bookRepository.save(new Book());
            bookRepository.save(new Book());
            bookRepository.save(new Book());
            bookRepository.save(new Book());

            mockMvc.perform(get("/books")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(greaterThanOrEqualTo(5)) )
            ) .andDo(print());
        }
    }


}