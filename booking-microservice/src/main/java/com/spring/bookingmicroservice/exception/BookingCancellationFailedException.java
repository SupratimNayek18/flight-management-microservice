package com.spring.bookingmicroservice.exception;

public class BookingCancellationFailedException extends Exception {
    public BookingCancellationFailedException(String s) {
        super(s);
    }
}
