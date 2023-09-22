package com.spring.bookingmicroservice.controller;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.exception.*;
import com.spring.bookingmicroservice.service.BookingService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookFlight/{flightId}/{userName}/{noOfPersons}")
    public ResponseEntity<BookingDto> bookFlight(@PathVariable Integer flightId,@PathVariable String userName,@PathVariable Integer noOfPersons) throws BookingFailedException, UserNameNotFoundException {

        return new ResponseEntity<>(bookingService.bookFlight(flightId,userName,noOfPersons), HttpStatus.OK);

    }

    @GetMapping("/getBookingDetails/{bookingId}")
    public ResponseEntity<BookingDto> getBookingDetails(@PathVariable Integer bookingId) throws BookingNotFoundException {
        return new ResponseEntity<>(bookingService.getBookingDetails(bookingId),HttpStatus.OK);
    }

    @GetMapping("/validateBooking/{bookingId}/{userName}")
    public ResponseEntity<BookingDto> validateBooking(@PathVariable Integer bookingId,@PathVariable String userName) throws InvalidBookingException {
        return new ResponseEntity<>(bookingService.validateBooking(bookingId,userName),HttpStatus.OK);
    }

    @PutMapping("/updateBookingCheckInStatus/{bookingId}")
    public ResponseEntity<BookingDto> updateBookingCheckInStatus(@PathVariable Integer bookingId) throws BookingNotFoundException {
        return new ResponseEntity<>(bookingService.updateBookingCheckInStatus(bookingId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteFlight/{bookingId}/{userName}")
    public ResponseEntity<String> cancelFlight(@PathVariable Integer bookingId,@PathVariable String userName) throws BookingCancellationFailedException {
        return new ResponseEntity<>(bookingService.cancelFlight(bookingId,userName),HttpStatus.OK);
    }

}
