package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.ReadBook;
import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.ReadBookRepository;
import com.demo.onlineLibraryAnaMariaDoroftei.repositories.UserRepository;
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
class UserControllerIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private ReadBookRepository readBookRepository;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Nested
    @DisplayName("saveUser()")
    class SaveUser{
        @Test
        public void shouldReturnSavedUser() throws Exception {
            User user = new User();

            mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(user))
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", greaterThanOrEqualTo(1))
            ) .andDo(print());
        }

        @Test
        public void shouldUpdateExistingUser() throws Exception{
            User existingUser = new User();
            existingUser.setEmail("abc@c.com");
            existingUser = userRepository.save(existingUser);
            long id = existingUser.getId();

            existingUser.setEmail("cde@c.com");


            mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(existingUser))
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$.id", equalTo(id), Long.class),
                    jsonPath("$.email", equalTo(existingUser.getEmail()))
            ) .andDo(print());
        }
    }

    @Nested
    @DisplayName("deleteUser()")
    class DeleteUser{

        @Test
        public void shouldDeleteExistingUser() throws Exception {
            User existingUser = new User();
            existingUser = userRepository.save(existingUser);
            long id = existingUser.getId();

            mockMvc.perform(delete("/users/" + id))
                    .andExpectAll(
                            status().isOk()
                    );
        }

        @Test
        public void shouldReturnNotFound() throws Exception{
            long id = 1234567897543L;

            mockMvc.perform(delete("/users/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getUser()")
    class GetUser{

        @Test
        public void shouldReturnUser() throws Exception{
            User existingUser = new User();
            existingUser = userRepository.save(existingUser);
            long id = existingUser.getId();

            mockMvc.perform(get("/users/" + id)
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
            long id = 1234567897543L;

            mockMvc.perform(delete("/users/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }

    @Nested
    @DisplayName("getUsers")
    class GetUsers{

        @Test
        public void shouldReturnUsers() throws Exception{
            userRepository.save(new User());
            userRepository.save(new User());
            userRepository.save(new User());


            mockMvc.perform(get("/users")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(greaterThanOrEqualTo(3)) )
            ) .andDo(print());
        }
    }

    @Nested
    @DisplayName("getReadBooksByUserId()")
    class GetBooksByUserId{

        @Test
        public void shouldReturn404WhenNotFindUserId() throws Exception{
            long id = 1234567897543L;


            mockMvc.perform(get("/users/" + id + "/readBooks")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isNotFound(),
                    content().contentType("application/json")
            ) .andDo(print());
        }

        @Test
        public void shouldReturnEmptyReadBooksListWhenUserExists() throws Exception {
            User existingUser = new User();
            existingUser = userRepository.save(existingUser);
            Long id = existingUser.getId();


            mockMvc.perform(get("/users/" + id + "/readBooks")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(existingUser))
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(equalTo(0)) )
            ) .andDo(print());
        }

        @Test
        public void shouldReturnAllReadBooksListWhenUserExists() throws Exception{
            User existingUser = new User();

            ReadBook bookOne = new ReadBook();
            ReadBook bookTwo = new ReadBook();
            existingUser.addReadBook(bookOne);
            existingUser.addReadBook(bookTwo);

            existingUser = userRepository.save(existingUser);
            Long id = existingUser.getId();


            mockMvc.perform(get("/users/" + id + "/readBooks")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(equalTo(2))),
                    jsonPath("$[0].id", equalTo(bookOne.getId()), Long.class),
                    jsonPath("$[1].id", equalTo(bookTwo.getId()), Long.class)
            ) .andDo(print());
        }

        @Test
        public void shouldReturnReadBooksListWithUnreadBooks() throws Exception{
            User existingUser = new User();

            ReadBook bookOne = new ReadBook();
            ReadBook bookTwo = new ReadBook();
            ReadBook bookThree = new ReadBook();
            ReadBook bookFour = new ReadBook();

            existingUser.addReadBook(bookOne);
            existingUser.addReadBook(bookTwo);
            existingUser.addReadBook(bookThree);

            existingUser = userRepository.save(existingUser);
            Long id = existingUser.getId();

            readBookRepository.save(bookFour);


            mockMvc.perform(get("/users/" + id + "/readBooks")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(equalTo(3))),
                    jsonPath("$[0].id", equalTo(bookOne.getId()), Long.class),
                    jsonPath("$[1].id", equalTo(bookTwo.getId()), Long.class),
                    jsonPath("$[2].id", equalTo(bookThree.getId()), Long.class)
            ) .andDo(print());
        }

        @Test
        public void shouldNotReturnReadBooksListWithUnreadBooks() throws Exception{
            User existingUser = new User();
            existingUser = userRepository.save(existingUser);
            Long id = existingUser.getId();

            ReadBook bookOne = new ReadBook();
            ReadBook bookTwo = new ReadBook();
            ReadBook bookThree = new ReadBook();
            ReadBook bookFour = new ReadBook();

            readBookRepository.save(bookOne);
            readBookRepository.save(bookTwo);
            readBookRepository.save(bookThree);
            readBookRepository.save(bookFour);

            mockMvc.perform(get("/users/" + id + "/readBooks")
                    .accept(MediaType.APPLICATION_JSON)
            ) .andExpectAll(
                    status().isOk(),
                    content().contentType("application/json"),
                    jsonPath("$", hasSize(equalTo(0)))
            ) .andDo(print());
        }
    }
}