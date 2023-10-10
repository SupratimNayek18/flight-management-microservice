package com.spring.bookingmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;

}
