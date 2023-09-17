package com.spring.bookingmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private Integer flightId;

    private String flightName;

    private String userName;

    private Integer seats;

    private String source;

    private String destination;

    private String date;

    private Double price;

    private String arrival;

    private String departure;

}
