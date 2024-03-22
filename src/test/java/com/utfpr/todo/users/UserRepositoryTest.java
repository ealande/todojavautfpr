package com.utfpr.todo.users;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByUsername_ReturnsUserByUsername() {
        // Given
        UserModel user = UserConstants.USER;
        testEntityManager.persist(user);
        testEntityManager.flush();

        // When
        UserModel foundUser = userRepository.findByUsername(user.getUsername());

        // Then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
    }

    // Add more test cases for UserRepository here
}
