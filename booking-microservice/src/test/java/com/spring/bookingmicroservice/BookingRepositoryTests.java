package com.spring.bookingmicroservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.bookingmicroservice.model.Booking;
import com.spring.bookingmicroservice.repository.BookingRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookingRepositoryTests {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void givenBookingShouldReturnBookingObject() {
        // Given
        Booking booking1 = new Booking("1", 123, "User1", true, false, null, null);

        // When
        bookingRepository.save(booking1); // Data is saved into the Database
        Booking booking2 = bookingRepository.findById(booking1.getBookingId()).orElse(null); // Data is retrieved from the Database

        // Then
        assertNotNull(booking2);
        assertEquals(booking1.getUserName(), booking2.getUserName());
    }


    @Test
    public void givenBookingIdShouldReturnBookingObject() {
        // Given
        Booking booking1 = new Booking("1", 123, "User1", true, false, null, null);

        // When
        bookingRepository.save(booking1); // Data is saved into the Database
        Optional<Booking> optionalBooking = bookingRepository.findById(booking1.getBookingId());

        // Then
        assertTrue(optionalBooking.isPresent());
        Booking retrievedBooking = optionalBooking.get();
        assertEquals(booking1.getUserName(), retrievedBooking.getUserName());
    }

    @Test
    public void givenNonExistentBookingIdShouldReturnEmptyOptional() {
        // When
        Optional<Booking> optionalBooking = bookingRepository.findById("NonExistentId");

        // Then
        assertFalse(optionalBooking.isPresent());
    }

    @Test
    public void givenUserNameShouldReturnBookingList() {
        // Given
        Booking booking1 = new Booking("1", 123, "User1", true, false, null, null);
        bookingRepository.save(booking1);

        // When
        List<Booking> bookingList = bookingRepository.findByUserName("User1");

        // Then
        assertTrue(!bookingList.isEmpty());
        assertEquals("User1", bookingList.get(0).getUserName());
    }

    @Test
    public void givenNonExistentUserNameShouldReturnEmptyList() {
        // When
        List<Booking> bookingList = bookingRepository.findByUserName("NonExistentUser");

        // Then
        assertTrue(bookingList.isEmpty());
    }
}
