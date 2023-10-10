//package com.spring.bookingmicroservice;
//
//import com.spring.bookingmicroservice.controller.BookingController;
//import com.spring.bookingmicroservice.dto.BookingDto;
//import com.spring.bookingmicroservice.exception.UserNameNotFoundException;
//import com.spring.bookingmicroservice.service.BookingService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(BookingController.class)
//public class BookingControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookingService bookingService;
//
//    @Test
//    public void testBookFlight() throws Exception, UserNameNotFoundException {
//        BookingDto bookingDto = new BookingDto(/* create a BookingDto object here */);
//        when(bookingService.bookFlight(anyInt(), anyString(), anyInt())).thenReturn(bookingDto);
//
//        mockMvc.perform(post("{flightId}/{userName}/{noOfPersons}", 1, "testUser", 2))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.property").value(bookingDto));
//    }
//}
