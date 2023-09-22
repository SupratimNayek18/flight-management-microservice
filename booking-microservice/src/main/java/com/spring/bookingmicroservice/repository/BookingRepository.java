package com.spring.bookingmicroservice.repository;

import com.spring.bookingmicroservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, Integer> {

    Booking findByUserName(String userName);

}
