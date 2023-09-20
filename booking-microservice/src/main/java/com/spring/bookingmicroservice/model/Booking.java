package com.spring.bookingmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    private Integer bookingId;
    private Integer flightId;
    private String userName;
    private boolean bookingStatus;
    private boolean checkInStatus=false;

}
