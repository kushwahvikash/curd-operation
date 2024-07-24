package com.vikas.co.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikas.co.entity.User;
import com.vikas.co.services.UserServices;

import jakarta.annotation.Nonnull;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    @Autowired
    private UserServices userService;

    // Create a new user
    @PostMapping("/add")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with ID " + user.getId() + " already exists");
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Nonnull Integer id) {
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    // Get users by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        List<User> users = userService.getUsersByName(name);
        return ResponseEntity.ok(users);
    }

    // Get users by email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> getUsersByEmail(@PathVariable String email) {
        List<User> users = userService.getUsersByEmail(email);
        return ResponseEntity.ok(users);
    }

    // Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @NonNull Integer id, @Validated @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @Nonnull Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete all users
    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }
}
