package com.spring.userregistrationmicroservice.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String userName;
    private String password;

}
