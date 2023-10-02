package com.spring.userregistrationmicroservice.service;

import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;

public interface UserService {

    UserDto register(User user);

    UserDto getUser(String userName);

    String deleteUser(String userName);
}
