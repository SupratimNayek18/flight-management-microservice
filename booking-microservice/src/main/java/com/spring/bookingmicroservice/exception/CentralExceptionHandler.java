package com.spring.bookingmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralExceptionHandler {

    @ExceptionHandler(value = BookingCancellationFailedException.class)
    public ResponseEntity<Object> bookingCancellationFailedException(BookingCancellationFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookingFailedException.class)
    public ResponseEntity<Object> bookingFailedException(BookingFailedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookingNotFoundException.class)
    public ResponseEntity<Object> bookingNotFoundException(BookingNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidBookingException.class)
    public ResponseEntity<Object> invalidBookingException(InvalidBookingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNameNotFoundException.class)
    public ResponseEntity<Object> userNameNotFoundException(UserNameNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
