package com.spring.bookingmicroservice;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.dto.CheckInDto;
import com.spring.bookingmicroservice.exception.BookingCancellationFailedException;
import com.spring.bookingmicroservice.exception.BookingNotFoundException;
import com.spring.bookingmicroservice.model.Booking;
import com.spring.bookingmicroservice.repository.BookingRepository;
import com.spring.bookingmicroservice.service.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class ServiceLayerTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec headersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec headersSpecMock;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookingDetailsWhenBookingPresent() throws BookingNotFoundException {

        Booking booking = new Booking();
        booking.setBookingId(1);

        BookingDto expectedDto = new BookingDto();
        expectedDto.setBookingId(1);

        // Mocking the behavior of bookingRepository
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));

        // Calling the getBookingDetails method
        BookingDto result = bookingService.getBookingDetails(1);

        // Verifying the result
        assertNotNull(result);
        assertEquals(expectedDto.getBookingId(), result.getBookingId());
    }

    @Test
    public void testGetBookingDetailsWhenBookingNotPresent(){
        when(bookingRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookingNotFoundException.class,()->bookingService.getBookingDetails(1));
    }

    @Test
    public void testCancelFlightWhenBookingNotNull() throws BookingCancellationFailedException, BookingNotFoundException {
        // Create a sample booking and check-in DTO
        Booking booking = new Booking();
        booking.setBookingId(1);
        booking.setUserName("user1");
        booking.setBookingStatus(true);
        booking.setCheckInStatus(true);
        booking.setFlightId(101);

        List<Booking> list = new ArrayList<>();
        list.add(booking);

        CheckInDto checkInDto = new CheckInDto();
        checkInDto.setCheckInStatus("true");

        // Mock the behavior of bookingRepository
        when(bookingRepository.findByUserName("user1")).thenReturn(list);
        doNothing().when(bookingRepository).deleteById(1);

        // Mock the WebClient for REST API call
        when(webClient.put()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpecMock);
        when(requestBodySpecMock.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(CheckInDto.class)).thenReturn(Mono.just(checkInDto));

        // Call the cancelFlight method
        String result = bookingService.cancelFlight(1, "user1");

        // Assert the result
        assertEquals("Booking with Booking Id : 1 cancelled successfully", result);
    }



}
