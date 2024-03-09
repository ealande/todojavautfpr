package com.utfpr.todo.users;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        return userOptional.map(user -> ResponseEntity.ok().body(user))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUserById(@PathVariable UUID id, @RequestBody UserModel user) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
    
            UserModel updatedUser = userRepository.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserModel user) {

        UserModel userModel = userRepository.findByUsername(user.getUsername());

        if (userModel != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Collections.singletonMap("error", "Username already exists"));
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        UserModel newUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
