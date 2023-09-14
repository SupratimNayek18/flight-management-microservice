package com.spring.userregistrationmicroservice.service;

import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //Registers new users and adds them to the database
    @Override
    public User register(User user) {

        //Encoding the user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

}
