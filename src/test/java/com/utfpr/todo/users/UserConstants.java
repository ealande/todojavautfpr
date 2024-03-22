package com.utfpr.todo.users;

import java.util.UUID;

public class UserConstants {
    public static final UserModel USER = UserModel.builder()
            .id(UUID.randomUUID())
            .username("testuser")
            .name("Test User")
            .email("test@example.com")
            .password("testpassword")
            .build();
}
