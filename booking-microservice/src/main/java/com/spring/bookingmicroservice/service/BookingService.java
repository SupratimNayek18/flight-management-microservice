package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.exception.BookingFailedException;
import com.spring.bookingmicroservice.exception.BookingNotFoundException;
import com.spring.bookingmicroservice.exception.InvalidBookingException;
import com.spring.bookingmicroservice.exception.UserNameNotFoundException;

public interface BookingService {

    BookingDto bookFlight(Integer flightId, String userName, Integer noOfPersons) throws BookingFailedException, UserNameNotFoundException;

    BookingDto getBookingDetails(Integer bookingId) throws BookingNotFoundException;

    Boolean validateBooking(Integer flightId,String userName) throws InvalidBookingException;

    String cancelFlight(Integer bookingId, String userName);

}
