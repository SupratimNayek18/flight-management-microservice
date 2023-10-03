package com.spring.userregistrationmicroservice;

import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import com.spring.userregistrationmicroservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserRegistrationMicroserviceApplicationTests {

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

}
