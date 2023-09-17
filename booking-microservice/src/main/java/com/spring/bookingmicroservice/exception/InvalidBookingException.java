package com.spring.bookingmicroservice.exception;

public class InvalidBookingException extends Exception {

    public InvalidBookingException(String bookingIsInvalid) {
        super(bookingIsInvalid);
    }

}
