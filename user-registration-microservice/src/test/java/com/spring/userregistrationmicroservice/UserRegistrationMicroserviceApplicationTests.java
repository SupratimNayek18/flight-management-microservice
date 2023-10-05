package com.spring.userregistrationmicroservice;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
=======
import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import com.spring.userregistrationmicroservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
>>>>>>> 0dad655e9542b94f8f8f3bdd6e0a8875af5447ac
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

<<<<<<< HEAD
import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import com.spring.userregistrationmicroservice.service.UserServiceImpl;
=======
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
>>>>>>> 0dad655e9542b94f8f8f3bdd6e0a8875af5447ac

@SpringBootTest
public class UserRegistrationMicroserviceApplicationTests {

<<<<<<< HEAD
    @Mock
    private UserRepository userRepository;
=======
	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegister() {
		// Creating mock register object
		User userToRegister = new User();
		userToRegister.setUserName("test");
		userToRegister.setPassword("test");

		// Mocking the behavior of passwordEncoder
		when(passwordEncoder.encode("test")).thenReturn("encodedPassword");

		// Mocking the behavior of userRepository
		when(userRepository.save(any(User.class))).thenReturn(userToRegister);

		// registering the user
		UserDto registeredUserDto = userService.register(userToRegister);

		// Assertions
		assertNotNull(registeredUserDto);
		assertEquals("test", registeredUserDto.getUserName());
	}

	@Test
	public void testGetUser(){
		User user = new User();
		user.setUserName("test");

		when(userRepository.findById("test")).thenReturn(Optional.of(user));

		UserDto userDto = userService.getUser("test");

		assertNotNull(userDto);
		assertEquals("test",userDto.getUserName());

	}

	@Test
	public void testDeleteUser() {
		// creating mock username
		String userNameToDelete = "test";

		when(userRepository.findById(userNameToDelete)).thenReturn(Optional.of(new User()));

		String result = userService.deleteUser(userNameToDelete);

		assertNotNull(result);
		assertEquals("User with username test deleted successfully", result);
		verify(userRepository, times(1)).delete(any(User.class));

	}

	@Test
	public void testDeleteUserNotFound() {
		// Arrange
		String userNameToDelete = "nonexistentuser";

		// Mock the behavior of userRepository.findById
		when(userRepository.findById(userNameToDelete)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(UsernameNotFoundException.class, () -> userService.deleteUser(userNameToDelete));
	}
>>>>>>> 0dad655e9542b94f8f8f3bdd6e0a8875af5447ac

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
