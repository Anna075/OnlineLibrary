package com.demo.onlineLibraryAnaMariaDoroftei.controllers;

import com.demo.onlineLibraryAnaMariaDoroftei.entities.User;
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
class UserControllerIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;
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
            Long id = existingUser.getId();

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
            Long id = existingUser.getId();

            mockMvc.perform(delete("/users/" + id))
                    .andExpectAll(
                            status().isOk()
                    );
        }

        @Test
        public void shouldReturnNotFound() throws Exception{
            Long id = 1234567897543L;

            mockMvc.perform(delete("/users/" + id))
                    .andExpectAll(
                            status().isNotFound()
                    );
        }
    }
    @Nested
    @DisplayName("getUser")
    class GetUser{

        @Test
        public void shouldReturnUser() throws Exception{
            User existingUser = new User();
            existingUser = userRepository.save(existingUser);
            Long id = existingUser.getId();

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
            Long id = 1234567897543L;

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
}