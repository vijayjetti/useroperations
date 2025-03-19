package com.vijay.learning.useroperations.controller;

import com.vijay.learning.useroperations.model.User;
import com.vijay.learning.useroperations.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void createUser_ShouldCreateNewUser() throws Exception {
        User user = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"john@example.com\"}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void getUser_ShouldReturnUser() throws Exception {
        User user = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        when(userService.getUser(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() throws Exception {
        User user1 = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        User user2 = User.builder().id(2L).name("Jane Doe").email("jane@example.com").build();
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    void updateUser_ShouldUpdateExistingUser() throws Exception {
        User updatedUser = User.builder().id(1L).name("John Updated").email("john.updated@example.com").build();
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Updated\",\"email\":\"john.updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"));
    }

    @Test
    void deleteUser_ShouldDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void isExistingUser_ShouldReturnTrueForExistingUser() throws Exception {
        when(userService.isExistingUser(1L)).thenReturn(Optional.of(new User()));

        mockMvc.perform(get("/api/v1/users/1/exists"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}