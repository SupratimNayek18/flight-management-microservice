package com.spring.bookingmicroservice.controller;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.exception.BookingFailedException;
import com.spring.bookingmicroservice.service.BookingService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookFlight/{flightId}/{userName}/{noOfPersons}")
    public ResponseEntity<BookingDto> bookFlight(@PathVariable Integer flightId,@PathVariable String userName,@PathVariable Integer noOfPersons) throws BookingFailedException {

        return new ResponseEntity<>(bookingService.bookFlight(flightId,userName,noOfPersons), HttpStatus.OK);

    }

}
