package com.utfpr.todo.users;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getAllUsers_ReturnsListOfUsers() {
        // Given
        when(userRepository.findAll()).thenReturn(Collections.singletonList(UserConstants.USER));

        // When
        List<UserModel> userList = userController.getAllUsers();

        // Then
        assertThat(userList).isNotNull();
        assertThat(userList).hasSize(1);
        assertThat(userList.get(0)).isEqualTo(UserConstants.USER);
    }

    @Test
    public void getUserById_WithValidId_ReturnsUser() {
        // Given
        UUID userId = UserConstants.USER.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.of(UserConstants.USER));

        // When
        ResponseEntity<UserModel> response = userController.getUserById(userId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(UserConstants.USER);
    }

    @Test
    public void getUserById_WithInvalidId_ReturnsNotFound() {
        // Given
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<UserModel> response = userController.getUserById(userId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    // Similarly, test cases for deleteUser, updateUserById, and create methods can be added here
}
