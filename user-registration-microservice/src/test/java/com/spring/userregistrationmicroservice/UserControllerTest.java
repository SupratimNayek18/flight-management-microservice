package com.spring.userregistrationmicroservice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.userregistrationmicroservice.controller.UserController;
import com.spring.userregistrationmicroservice.dto.SignInResponse;
import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.request.JwtRequest;
import com.spring.userregistrationmicroservice.service.UserService;
import com.spring.userregistrationmicroservice.util.JwtUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testRegisterUser() throws Exception {
        // Test registering user
        User user = new User("testUser", "password", "ROLE_USER", "John", "Doe", "Address", 123456789L);
        when(userService.register(user)).thenReturn(new UserDto("testUser", "John", "Doe", "Address", 123456789L));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("testUser"));
    }
}

