package com.vijay.learning.useroperations.controller;

import com.vijay.learning.useroperations.model.User;
import com.vijay.learning.useroperations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.vijay.learning.useroperations.constants.URLConstants.*;

@RestController
@RequestMapping(value = BASE_URL)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = ADD_USER)
    public void createUser(@RequestBody User user) {
        log.info("Add user: userId: {}, name: {}", user.getId(), user.getName());
        userService.createUser(user);

    }
    @GetMapping(value = GET_USER)
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping(value = UPDATE_USER)
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = DELETE_USER)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

    }

    @GetMapping(value = GET_USER_LIST)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = CHECK_EXISTING_USER)
    public Boolean isExistingUser(@PathVariable Long id) {
        return userService.isExistingUser(id).isPresent();
    }
}
