package com.spring.bookingmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDto {

    private Integer flightId;
    private String userName;
    private String checkInStatus;
    private List<String> seatsBooked;

}
