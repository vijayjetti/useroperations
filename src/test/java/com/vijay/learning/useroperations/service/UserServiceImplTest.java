package com.vijay.learning.useroperations.service;

import com.vijay.learning.useroperations.model.User;
import com.vijay.learning.useroperations.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserExists() {
        User user = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUser(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        List<User> users = Arrays.asList(
            User.builder().id(1L).name("John Doe").email("john@example.com").build(),
            User.builder().id(2L).name("Jane Doe").email("jane@example.com").build()
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        User userToCreate = User.builder().name("John Doe").email("john@example.com").build();
        User savedUser = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(userToCreate);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void updateUser_ShouldUpdateExistingUser() {
        User existingUser = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        User updatedUser = User.builder().id(1L).name("John Updated").email("john.updated@example.com").build();
        
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("John Updated", result.getName());
        assertEquals("john.updated@example.com", result.getEmail());
    }

    @Test
    void deleteUser_ShouldDeleteExistingUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void isExistingUser_ShouldReturnTrue_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));

        Optional<User> result = userService.isExistingUser(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void isExistingUser_ShouldReturnFalse_WhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.isExistingUser(1L);

        assertFalse(result.isPresent());
    }
}