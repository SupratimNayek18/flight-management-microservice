package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.dto.BookingDto;
import com.spring.bookingmicroservice.dto.FlightDto;
import com.spring.bookingmicroservice.dto.UserDto;
import com.spring.bookingmicroservice.exception.*;
import com.spring.bookingmicroservice.model.Booking;
import com.spring.bookingmicroservice.repository.BookingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.Random;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WebClient webClient;

    @Override
    @CircuitBreaker(name = "bookFlightCircuitBreaker",fallbackMethod = "bookFlightFallBack")
    public BookingDto bookFlight(Integer flightId, String userName, Integer noOfPersons) throws BookingFailedException, UserNameNotFoundException {

        //Validating whether the username exists or not
        UserDto userDto = webClient.get()
                                    .uri("http://localhost:8080/user/getUser/"+userName)
                                    .retrieve()
                                    .bodyToMono(UserDto.class)
                                    .block();

        if(userDto==null){
            throw new UserNameNotFoundException("User with username not found");
        }

        Booking booking = new Booking();
        booking.setFlightId(flightId);
        booking.setUserName(userName);

        //Retrieving Flight Details using webclient from flight microservice
        FlightDto flightDto = webClient.get()
                .uri("http://localhost:8081/flight/viewByFlightId/"+flightId)
                .retrieve()
                .bodyToMono(FlightDto.class)
                .block();


        if(flightDto!=null){

            double flightCost = flightDto.getPrice() * noOfPersons;

            //Making Payment rest api call for making payment and setting payment status if successful
            Boolean result = webClient.get()
                    .uri("http://localhost:8084/payment/doPayment/1/"+userName+"/"+flightCost)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();


            if(result){

                booking.setBookingStatus(true);

                booking.setBookingId(new Random().nextInt());

                Booking savedBooking = bookingRepository.save(booking);

                return modelMapper.map(savedBooking,BookingDto.class);

            }

        }

        throw new BookingFailedException("Booking Could Not Be Processed. Please Try Again");

    }

    //Fallback method for bookFlight method
    public BookingDto bookFlightFallBack(Integer flightId, String userName, Integer noOfPersons,Exception e) {
        return new BookingDto();
    }

    @Override
    public BookingDto getBookingDetails(Integer bookingId) throws BookingNotFoundException {

        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if(booking.isPresent()){
            return modelMapper.map(booking,BookingDto.class);
        }

        throw new BookingNotFoundException("Booking with given id not found");

    }

    /*
        This method will be called from Check In Service to validate the booking
        hence also setting the check in status to true
    */
    @Override
    public Boolean validateBooking(Integer flightId, String userName) throws InvalidBookingException {

        Booking booking = bookingRepository.findByUserName(userName);

        if(booking!=null){
            if(booking.getFlightId()==flightId){
                booking.setCheckInStatus(true);
                bookingRepository.save(booking);
                return true;
            }
        }

        throw new InvalidBookingException("Booking is Invalid");

    }


    /*  If checked in we must make a rest call to check in service
        to restore the number of cancelled seats and seat numbers
        else simple deleting the booking from db
     */
    @Override
    public String cancelFlight(Integer bookingId, String userName) throws BookingCancellationFailedException {

        Booking booking = bookingRepository.findByUserName(userName);

        if(booking!=null){

            if(booking.isCheckInStatus()){

                //TODO Make a rest api call to check in service to restore the number of seats

            }

            bookingRepository.deleteById(bookingId);

            return "Booking with Booking Id : "+bookingId+" cancelled successfully";

        }

        throw new BookingCancellationFailedException("Booking cancellation failed. Please try again");

    }

}
