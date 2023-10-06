package com.spring.userregistrationmicroservice.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String userWithUsernameAlreadyExists) {
        super(userWithUsernameAlreadyExists);
    }
}
