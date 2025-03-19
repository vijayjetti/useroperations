package com.vijay.learning.useroperations.controller;

import static com.vijay.learning.useroperations.constants.ApiConstants.*;

import com.vijay.learning.useroperations.model.User;
import com.vijay.learning.useroperations.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = BASE_URL)
@Slf4j
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = ADD_USER)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping(value = GET_USER)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUser(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping(value = UPDATE_USER)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable @NotNull Long id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping(value = DELETE_USER)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @NotNull Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = GET_USER_LIST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = CHECK_EXISTING_USER)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isExistingUser(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.isExistingUser(id).isPresent());
    }
}