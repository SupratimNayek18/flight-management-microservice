package com.spring.bookingmicroservice.service;

import com.spring.bookingmicroservice.dto.*;
import com.spring.bookingmicroservice.exception.*;
import com.spring.bookingmicroservice.model.Booking;
import com.spring.bookingmicroservice.repository.BookingRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.awt.print.Book;
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

        System.out.println("Method Called");

        //Validating whether the username exists or not
        UserDto userDto = webClient.get()
                                    .uri("http://localhost:8080/user/getUser/"+userName)
                                    .retrieve()
                                    .bodyToMono(UserDto.class)
                                    .block();

        System.out.println(userDto.getUserName());


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

        System.out.println(flightDto.getFlightId());

        if(flightDto!=null){

            double flightCost = flightDto.getPrice() * noOfPersons;

            //Making Payment rest api call for making payment and setting payment status if successful
            PaymentDto paymentDtoAfterPayment = webClient.get()
                    .uri("http://localhost:8084/payment/doPayment/"+userName+"/"+flightCost)
                    .retrieve()
                    .bodyToMono(PaymentDto.class)
                    .block();

            if(paymentDtoAfterPayment!=null){

                int transactionId = paymentDtoAfterPayment.getTransactionId();

                booking.setBookingStatus(true);

                Integer bookingId = new Random().nextInt();

                booking.setBookingId(bookingId);

                Booking savedBooking = bookingRepository.save(booking);

                PaymentDto paymentDto = webClient.get()
                        .uri("http://localhost:8084/payment/getByTransactionId/"+transactionId)
                        .retrieve()
                        .bodyToMono(PaymentDto.class)
                        .block();

                System.out.println("Line Number 96 :"+paymentDto.getTransactionId());

                if(paymentDto!=null){

                    paymentDto.setBookingId(bookingId);

                    PaymentDto paymentDto1 = webClient.put()
                            .uri("http://localhost:8084/payment/update/"+transactionId)
                            .body(Mono.just(paymentDto) , PaymentDto.class)
                            .retrieve()
                            .bodyToMono(PaymentDto.class)
                            .block();

                }

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
    */
    @Override
    public BookingDto validateBooking(Integer bookingId, String userName) throws InvalidBookingException {

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if(optionalBooking.isPresent()){

            Booking booking = optionalBooking.get();

            if(booking.getUserName().equals(userName)){

                return modelMapper.map(booking,BookingDto.class);

            }

        }

        return null;

    }


    /*  If checked in we must make a rest call to check in service
        to restore the number of cancelled seats and seat numbers
        else simple deleting the booking from db
     */
    @Override
    public String cancelFlight(Integer bookingId, String userName) throws BookingCancellationFailedException {

        Booking booking = bookingRepository.findByUserName(userName);

        System.out.println(booking.getBookingId());
        System.out.println(bookingId);
        System.out.println(booking.getBookingId().equals(bookingId));

        if(booking!=null && booking.getBookingId().equals(bookingId)){

            if(booking.getCheckInStatus()){

                //Making a rest api call to check in service to restore the number of seats
                CheckInDto checkInDto = webClient.put()
                        .uri("http://localhost:8083/checkIn/cancelCheckIn/"+booking.getFlightId())
                        .retrieve()
                        .bodyToMono(CheckInDto.class)
                        .block();

            }

            bookingRepository.deleteById(bookingId);

            return "Booking with Booking Id : "+bookingId+" cancelled successfully";

        }

        throw new BookingCancellationFailedException("Booking cancellation failed. Please try again");

    }

    @Override
    public BookingDto updateBookingCheckInStatus(Integer bookingId) throws BookingNotFoundException {

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if(optionalBooking.isPresent()){

            Booking booking = optionalBooking.get();

            booking.setCheckInStatus(true);

            Booking updatedBooking = bookingRepository.save(booking);

            return modelMapper.map(updatedBooking,BookingDto.class);
        }

        throw new BookingNotFoundException("Booking with id "+bookingId+" not found");

    }

}
