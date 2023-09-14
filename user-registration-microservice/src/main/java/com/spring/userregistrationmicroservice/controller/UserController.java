package com.spring.userregistrationmicroservice.controller;

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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> signIn(@RequestBody JwtRequest jwtRequest){

        try {

            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));

            return new ResponseEntity<>(jwtUtil.generateToken(jwtRequest.getUserName()), HttpStatus.OK);

        }
        catch (Exception e){
            throw new UsernameNotFoundException("Username invalid");
        }

    }

    //Endpoint to register new user
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(userService.register(user));
    }

}
