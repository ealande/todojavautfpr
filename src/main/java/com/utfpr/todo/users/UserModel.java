package com.utfpr.todo.users;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;



@Data //create all the most important methods in java
@Entity(name = "tbl-users")
public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String username;
    private String name;
    private String email;
    private String password;
    
    @CreationTimestamp
    private LocalDateTime createAT;
}
