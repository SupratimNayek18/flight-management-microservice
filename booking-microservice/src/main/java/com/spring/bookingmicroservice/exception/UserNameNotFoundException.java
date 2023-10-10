package com.spring.bookingmicroservice.exception;

public class UserNameNotFoundException extends Throwable {
    public UserNameNotFoundException(String userWithUsernameNotFound) {
        super(userWithUsernameNotFound);
    }
}
