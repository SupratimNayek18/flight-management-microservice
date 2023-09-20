package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.exception.*;

public interface BookingService {

    BookingDto bookFlight(Integer flightId, String userName, Integer noOfPersons) throws BookingFailedException, UserNameNotFoundException;

    BookingDto getBookingDetails(Integer bookingId) throws BookingNotFoundException;

    Boolean validateBooking(Integer flightId,String userName) throws InvalidBookingException;

    String cancelFlight(Integer bookingId, String userName) throws BookingCancellationFailedException;

}
