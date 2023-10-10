package com.spring.bookingmicroservice.repository;

import com.spring.bookingmicroservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByUserName(String userName);

}
