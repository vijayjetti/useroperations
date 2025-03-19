package com.vijay.learning.useroperations.service;

import com.vijay.learning.useroperations.exception.ResourceNotFoundException;
import com.vijay.learning.useroperations.model.User;
import com.vijay.learning.useroperations.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        log.debug("Fetching user with id: {}", id);
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<User> getAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        log.debug("Creating new user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        log.debug("Updating user with id: {}", id);
        userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.debug("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> isExistingUser(Long id) {
        log.debug("Checking if user exists with id: {}", id);
        return userRepository.findById(id);
    }
}