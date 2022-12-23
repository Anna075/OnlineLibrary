package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.Book;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookCategoryRepository;
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
@SuppressWarnings("unused")
class BookControllerIT {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

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
            long id = 123123123123123L;

            mockMvc.perform(delete("/books/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getBook()")
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
            long id = 123123123123L;

            mockMvc.perform(get("/books/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getBooks()")
    class GetBooks{

        @Test
        public void shouldReturnAllBooks() throws Exception{
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

        @Test
        public void shouldReturn404WhenBookCategoryNotFound() throws Exception {
            String categoryName = "Not Existing Category";

            mockMvc.perform(get("/books").queryParam("categoryName", categoryName))
                    .andExpectAll(status().isNotFound());
        }

        @Test
        public void shouldReturnEmptyBooksListWhenBooksNotFound() throws Exception {
            BookCategory fantasyBookCategory = new BookCategory();
            fantasyBookCategory.setName("fantasy");
            fantasyBookCategory = bookCategoryRepository.save(fantasyBookCategory);

            BookCategory itBookCategory = new BookCategory();
            itBookCategory.setName("it");
            itBookCategory = bookCategoryRepository.save(itBookCategory);

            Book bookOne = new Book();
            bookOne.setBookCategory(itBookCategory);
            bookRepository.save(bookOne);

            Book bookTwo = new Book();
            bookTwo.setBookCategory(itBookCategory);
            bookRepository.save(bookTwo);

            String categoryName = fantasyBookCategory.getName();

            mockMvc.perform(get("/books")
                    .queryParam("categoryName", categoryName)
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", empty())
            ) .andDo(print());
        }

        @Test
        public void shouldReturnBooksWhenBookCategoryExists() throws Exception {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setName("it");
            bookCategory = bookCategoryRepository.save(bookCategory);

            Book bookOne = new Book();
            bookOne.setBookCategory(bookCategory);
            bookOne = bookRepository.save(bookOne);

            Book bookTwo = new Book();
            bookTwo.setBookCategory(bookCategory);
            bookTwo = bookRepository.save(bookTwo);

            Book bookThree = new Book();
            bookThree.setBookCategory(bookCategory);
            bookThree = bookRepository.save(bookThree);

            String categoryName = bookCategory.getName();

            mockMvc.perform(get("/books")
                    .queryParam("categoryName", categoryName)
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(equalTo(3))),
                    jsonPath("$[0].bookCategory.name", equalTo(bookOne.getBookCategory().getName()), String.class),
                    jsonPath("$[1].bookCategory.name", equalTo(bookTwo.getBookCategory().getName()), String.class),
                    jsonPath("$[2].bookCategory.name", equalTo(bookThree.getBookCategory().getName()), String.class)
            ) .andDo(print());
        }

        @Test
        public void shouldReturnKidsBooksWhenMoreBookCategoryExist() throws Exception {
            BookCategory itBookCategory = new BookCategory();
            itBookCategory.setName("it");
            itBookCategory = bookCategoryRepository.save(itBookCategory);

            BookCategory kidsBookCategory = new BookCategory();
            kidsBookCategory.setName("kids");
            kidsBookCategory = bookCategoryRepository.save(kidsBookCategory);

            Book bookOne = new Book();
            bookOne.setBookCategory(itBookCategory);
            bookRepository.save(bookOne);

            Book bookTwo = new Book();
            bookTwo.setBookCategory(itBookCategory);
            bookRepository.save(bookTwo);

            Book bookThree = new Book();
            bookThree.setBookCategory(kidsBookCategory);
            bookThree = bookRepository.save(bookThree);

            Book bookFour = new Book();
            bookFour.setBookCategory(kidsBookCategory);
            bookFour = bookRepository.save(bookFour);

            Book bookFive = new Book();
            bookFive.setBookCategory(kidsBookCategory);
            bookFive = bookRepository.save(bookFive);

            String categoryName = kidsBookCategory.getName();

            mockMvc.perform(get("/books")
                    .queryParam("categoryName", categoryName)
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(equalTo(3))),
                    jsonPath("$[0].bookCategory.name", equalTo(bookThree.getBookCategory().getName()), String.class),
                    jsonPath("$[1].bookCategory.name", equalTo(bookFour.getBookCategory().getName()), String.class),
                    jsonPath("$[2].bookCategory.name", equalTo(bookFive.getBookCategory().getName()), String.class)
            ) .andDo(print());
        }

    }


}