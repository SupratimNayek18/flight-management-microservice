//package com.spring.bookingmicroservice;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.spring.bookingmicroservice.model.Booking;
//import com.spring.bookingmicroservice.repository.BookingRepository;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class BookingRepositoryTests {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Test
//    public void givenBookingShouldReturnBookingObject() {
//        // Given
//
//        Booking booking1 = new Booking("1", 123, "User1", true, false, null, null);
//
//        Booking booking1 = new Booking("1", 123, "User1", true, false, null,2);
//
//
//        // When
//        bookingRepository.save(booking1); // Data is saved into the Database
//        Booking booking2 = bookingRepository.findById(booking1.getBookingId()).orElse(null); // Data is retrieved from the Database
//
//        // Then
//        assertNotNull(booking2);
//        assertEquals(booking1.getUserName(), booking2.getUserName());
//    }
//
//<<<<<<< HEAD
//=======
//    @Test
//    public void getAllMustReturnAllBookings() {
//        // Given
//        Booking booking3 = new Booking("2", 456, "User2", true, false, null,2);
//        Booking booking4 = new Booking("3", 789, "User3", true, false, null,2);
//        bookingRepository.save(booking3); // Save the Data in the Database
//        bookingRepository.save(booking4); // Save the Data in the Database
//
//        // When
//        List<Booking> bookingList = bookingRepository.findAll();
//
//        // Then
//        assertEquals(4, bookingList.size());
//        assertEquals("User3", bookingList.get(3).getUserName());
//    }
//>>>>>>> 0dad655e9542b94f8f8f3bdd6e0a8875af5447ac
//
//    @Test
//    public void givenBookingIdShouldReturnBookingObject() {
//        // Given
//<<<<<<< HEAD
//        Booking booking1 = new Booking("1", 123, "User1", true, false, null, null);
//=======
//        Booking booking1 = new Booking("1", 123, "User1", true, false, null,2);
//>>>>>>> 0dad655e9542b94f8f8f3bdd6e0a8875af5447ac
//
//        // When
//        bookingRepository.save(booking1); // Data is saved into the Database
//        Optional<Booking> optionalBooking = bookingRepository.findById(booking1.getBookingId());
//
//        // Then
//        assertTrue(optionalBooking.isPresent());
//        Booking retrievedBooking = optionalBooking.get();
//        assertEquals(booking1.getUserName(), retrievedBooking.getUserName());
//    }
//
//    @Test
//    public void givenNonExistentBookingIdShouldReturnEmptyOptional() {
//        // When
//        Optional<Booking> optionalBooking = bookingRepository.findById("NonExistentId");
//
//        // Then
//        assertFalse(optionalBooking.isPresent());
//    }
//
//    @Test
//    public void givenUserNameShouldReturnBookingList() {
//        // Given
//<<<<<<< HEAD
//        Booking booking1 = new Booking("1", 123, "User1", true, false, null, null);
//=======
//        Booking booking1 = new Booking("1", 123, "User1", true, false, null,2);
//>>>>>>> 0dad655e9542b94f8f8f3bdd6e0a8875af5447ac
//        bookingRepository.save(booking1);
//
//        // When
//        List<Booking> bookingList = bookingRepository.findByUserName("User1");
//
//        // Then
//        assertTrue(!bookingList.isEmpty());
//        assertEquals("User1", bookingList.get(0).getUserName());
//    }
//
//    @Test
//    public void givenNonExistentUserNameShouldReturnEmptyList() {
//        // When
//        List<Booking> bookingList = bookingRepository.findByUserName("NonExistentUser");
//
//        // Then
//        assertTrue(bookingList.isEmpty());
//    }
//}
