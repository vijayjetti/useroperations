package com.vijay.learning.useroperations.service;

import com.vijay.learning.useroperations.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    Optional<User> isExistingUser(Long id);

    void deleteUser(Long id);
}
