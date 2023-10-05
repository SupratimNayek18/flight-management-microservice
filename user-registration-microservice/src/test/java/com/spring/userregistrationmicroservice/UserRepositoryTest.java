package com.spring.userregistrationmicroservice;

import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByUsername() {
        // Test finding user by username
        User user = new User("testUser", "password", "ROLE_USER", "John", "Doe", "Address", 123456789L);
        userRepository.save(user);

        Optional<User> result = userRepository.findById("testUser");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());
    }

    @Test
    public void testFindUserByUsernameNotFound() {
        // Test finding nonexistent user by username
        Optional<User> result = userRepository.findById("nonexistentUser");

        assertFalse(result.isPresent());
    }
}

