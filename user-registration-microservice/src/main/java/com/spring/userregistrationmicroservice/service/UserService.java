package com.spring.userregistrationmicroservice.service;

import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;
import com.spring.userregistrationmicroservice.exception.UserAlreadyExistsException;

public interface UserService {

    UserDto register(User user) throws UserAlreadyExistsException;

    UserDto getUser(String userName);

    UserDto updateUser(UserDto userDto);

    String deleteUser(String userName);
}
