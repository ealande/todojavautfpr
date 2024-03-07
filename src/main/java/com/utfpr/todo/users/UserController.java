package com.utfpr.todo.users;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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


    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserModel user) {

        UserModel userModel = userRepository.findByUsername(user.getUsername());

        if (userModel != null) {
            // throw new RuntimeErrorException(null, "Username already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonMap("error", "Username already exists"));
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        UserModel newUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
