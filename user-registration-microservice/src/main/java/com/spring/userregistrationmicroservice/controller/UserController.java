package com.spring.userregistrationmicroservice.controller;

import com.spring.userregistrationmicroservice.dto.SignInResponse;
import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.request.JwtRequest;
import com.spring.userregistrationmicroservice.service.UserService;
import com.spring.userregistrationmicroservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    //Endpoint to receive jwt token correct username and password
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody JwtRequest jwtRequest){

        try {

            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));

            SignInResponse signInResponse = new SignInResponse();

            String token = jwtUtil.generateToken(jwtRequest.getUserName());
            String userName = jwtRequest.getUserName();


            signInResponse.setUserName(userName);
            signInResponse.setToken(token);
            signInResponse.setRole(String.valueOf(new ArrayList<>(authentication.getAuthorities()).get(0)));

            return new ResponseEntity<>(signInResponse, HttpStatus.OK);

        }
        catch (Exception e){
            throw new UsernameNotFoundException("Username invalid");
        }

    }

    //Endpoint to register new user
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody User user){
        return ResponseEntity.ok(userService.register(user));
    }

    //Endpoint to get user details with username
    @GetMapping("/getUser/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userName){
        return new ResponseEntity<>(userService.getUser(userName),HttpStatus.OK);
    }

}
