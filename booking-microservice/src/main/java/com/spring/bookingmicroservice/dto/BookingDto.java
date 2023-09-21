package com.spring.bookingmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private String bookingId;
    private String flightId;
    private String userName;
    private Boolean bookingStatus;
    private Boolean checkInStatus;

}
