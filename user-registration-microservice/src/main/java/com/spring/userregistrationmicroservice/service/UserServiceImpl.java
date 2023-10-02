package com.spring.userregistrationmicroservice.service;

import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.repository.UserRepository;
import com.spring.userregistrationmicroservice.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //Registers new users and adds them to the database
    @Override
    public UserDto register(User user) {

        //Encoding the user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return EntityToDto.convertToDto(savedUser);

    }

    @Override
    public UserDto getUser(String userName) {

        Optional<User> user = userRepository.findById(userName);

        if(user.isPresent()){

            return EntityToDto.convertToDto(user.get());
        }
        throw new UsernameNotFoundException("User with username not found");

    }

    @Override
    public String deleteUser(String userName) {
        Optional<User> user = userRepository.findById(userName);

        if(user.isPresent()){
            userRepository.delete(user.get());
            return "User with username "+userName+" deleted successfully";
        }

        throw new UsernameNotFoundException("User Not Found");

    }

}
