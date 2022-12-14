package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.BookCategory;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookCategoryRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Transactional
class BookCategoryControllerIT {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private BookCategoryController bookCategoryController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(bookCategoryController).build();
    }

    @Nested
    @DisplayName("saveBookCategory()")
    class SaveBookCategory{
        @Test
        public void shouldReturnSavedBookCategory() throws Exception {
            BookCategory bookCategory = new BookCategory();

            mockMvc.perform(post("/bookCategories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(bookCategory))
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", greaterThanOrEqualTo(1))
            ) .andDo(print());
        }

        @Test
        public void shouldUpdateExistingBookCategory() throws Exception{
            BookCategory existingBookCategory = new BookCategory();
            existingBookCategory.setName("Fiction2");
            existingBookCategory = bookCategoryRepository.save(existingBookCategory);
            Long id = existingBookCategory.getId();

            existingBookCategory.setName("Fiction");


            mockMvc.perform(post("/bookCategories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(existingBookCategory))
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", equalTo(id), Long.class),
                    jsonPath("$.name", equalTo(existingBookCategory.getName()))
            ) .andDo(print());
        }
    }

    @Nested
    @DisplayName("deleteBookCategory()")
    class DeleteBookCategory{

        @Test
        public void shouldDeleteExistingBookCategory() throws Exception {
            BookCategory existingBookCategory = new BookCategory();
            existingBookCategory = bookCategoryRepository.save(existingBookCategory);
            Long id = existingBookCategory.getId();

            mockMvc.perform(delete("/bookCategories/" + id))
                    .andExpectAll(
                            status().isOk()
                    );
        }

        @Test
        public void shouldReturnNotFound() throws Exception{
            Long id = 1234567897543L;

            mockMvc.perform(delete("/bookCategories/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getBookCategory")
    class GetBookCategory{

        @Test
        public void shouldReturnBookCategory() throws Exception{
            BookCategory existingBookCategory = new BookCategory();
            existingBookCategory = bookCategoryRepository.save(existingBookCategory);
            Long id = existingBookCategory.getId();

            mockMvc.perform(get("/bookCategories/" + id)
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
            Long id = 1234567897543L;

            mockMvc.perform(delete("/bookCategory/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }
    @Nested
    @DisplayName("getBookCategories")
    class GetBookCategories{

        @Test
        public void shouldReturnBookCategories() throws Exception{
            bookCategoryRepository.save(new BookCategory());
            bookCategoryRepository.save(new BookCategory());
            bookCategoryRepository.save(new BookCategory());
            bookCategoryRepository.save(new BookCategory());
            bookCategoryRepository.save(new BookCategory());
            bookCategoryRepository.save(new BookCategory());


            mockMvc.perform(get("/bookCategories")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(greaterThanOrEqualTo(6)) )
            ) .andDo(print());
        }
    }


}