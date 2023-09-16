package com.spring.userregistrationmicroservice.util;

import com.spring.userregistrationmicroservice.dto.UserDto;
import com.spring.userregistrationmicroservice.entity.User;

public class EntityToDto {

    public static UserDto convertToDto(User user){
        return new UserDto(user.getUserName(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getPhoneNumber());
    }

}
