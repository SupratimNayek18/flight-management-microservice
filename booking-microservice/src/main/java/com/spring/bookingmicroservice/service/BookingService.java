package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.exception.*;
import com.spring.bookingmicroservice.model.Booking;

import java.util.List;

public interface BookingService {

    BookingDto bookFlight(Integer flightId, String userName, Integer noOfPersons) throws BookingFailedException, UserNameNotFoundException;

    BookingDto getBookingDetails(String bookingId) throws BookingNotFoundException;

    List<Booking> getBookingByUserName(String userName) throws BookingNotFoundException;

    BookingDto validateBooking(String bookingId, String userName) throws InvalidBookingException;

    String cancelFlight(String bookingId, String userName) throws BookingCancellationFailedException, BookingNotFoundException;

    BookingDto updateBookingCheckInStatus(String bookingId,String checkInId) throws BookingNotFoundException;

}
