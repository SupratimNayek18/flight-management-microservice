package com.spring.userregistrationmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import com.spring.userregistrationmicroservice.service.UserServiceImpl;

@SpringBootTest
public class UserRegistrationMicroserviceApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testRegisterUser() {
        // Test user registration
        User user = new User("testUser", "password", "ROLE_USER", "John", "Doe", "Address", 123456789L);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        UserDto result = userService.register(user);

        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
    }

    @Test
    public void testGetUserByUsername() {
        // Test getting user by username
        User user = new User("testUser", "password", "ROLE_USER", "John", "Doe", "Address", 123456789L);
        when(userRepository.findById("testUser")).thenReturn(Optional.of(user));

        UserDto result = userService.getUser("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        // Test getting user by nonexistent username
        when(userRepository.findById("nonexistentUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getUser("nonexistentUser"));
    }

    @Test
    public void testDeleteUser() {
        // Test deleting user
        User user = new User("testUser", "password", "ROLE_USER", "John", "Doe", "Address", 123456789L);
        when(userRepository.findById("testUser")).thenReturn(Optional.of(user));

        String result = userService.deleteUser("testUser");

        assertEquals("User with username testUser deleted successfully", result);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Test deleting nonexistent user
        when(userRepository.findById("nonexistentUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.deleteUser("nonexistentUser"));
    }
}
