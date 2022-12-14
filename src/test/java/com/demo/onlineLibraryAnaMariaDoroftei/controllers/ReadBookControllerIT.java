package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.BookRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.ReadBookRepository;
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
class ReadBookControllerIT {

    @Autowired
    private ReadBookRepository readBookRepository;

    private BookRepository bookRepository;

    @Autowired
    private ReadBookController readBookController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(readBookController).build();
    }

    @Nested
    @DisplayName("saveReadBook()")
    class SaveReadBook {
        @Test
        public void shouldReturnSavedReadBook() throws Exception {
            ReadBook readBook = new ReadBook();

            mockMvc.perform(post("/readBooks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(readBook))
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", greaterThanOrEqualTo(1))
            ).andDo(print());
        }

        @Test
        public void shouldUpdateExistingReadBook() throws Exception {
            ReadBook existingReadBook = new ReadBook();
            existingReadBook = readBookRepository.save(existingReadBook);

            mockMvc.perform(post("/readBooks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(existingReadBook))
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpectAll(
                    status().isForbidden(),
                    content().contentType("application/json")
            ).andDo(print());

        }
    }

    @Nested
    @DisplayName("deleteReadBook()")
    class DeleteReadBook {

        @Test
        public void shouldDeleteExistingReadBook() throws Exception {
            ReadBook existingReadBook = new ReadBook();
            existingReadBook = readBookRepository.save(existingReadBook);
            Long id = existingReadBook.getId();

            mockMvc.perform(delete("/readBooks/" + id))
                    .andExpectAll(
                            status().isOk()
                    );
        }

        @Test
        public void shouldReturnNotFound() throws Exception {
            Long id = 1234567897543L;

            mockMvc.perform(delete("/readBooks/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getReadBook")
    class GetReadBook {

        @Test
        public void shouldReturnReadBookr() throws Exception {
            ReadBook existingReadBook = new ReadBook();
            existingReadBook = readBookRepository.save(existingReadBook);
            Long id = existingReadBook.getId();

            mockMvc.perform(get("/readBooks/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", equalTo(id), Long.class)
            ).andDo(print());
        }

        @Test
        public void shouldReturnNotFound() throws Exception {
            Long id = 1234567897543L;

            mockMvc.perform(delete("/readBooks/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getReadBooks")
    class GetReadBooks{

        @Test
        public void shouldReturnReadBooks() throws Exception{
            readBookRepository.save(new ReadBook());
            readBookRepository.save(new ReadBook());


            mockMvc.perform(get("/readBooks")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(greaterThanOrEqualTo(2)) )
            ) .andDo(print());
        }
    }
}