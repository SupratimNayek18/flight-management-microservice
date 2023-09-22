package capgemini.na.checkIn.service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import capgemini.na.checkIn.dto.BookingDto;
import capgemini.na.checkIn.exception.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import capgemini.na.checkIn.dto.FlightDto;
import capgemini.na.checkIn.exception.AlreadyCheckedInException;
import capgemini.na.checkIn.model.CheckIn;
import capgemini.na.checkIn.repository.CheckInRepository;
import reactor.core.publisher.Mono;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    WebClient webClient;
    @Autowired
    CheckInRepository repository;

    @Override
    public CheckIn checkIn(int bookingId, String userName, List<String> seatNumbers) throws AlreadyCheckedInException, BookingNotFoundException {

        //Getting booking details using booking id for which user has not checked in
        BookingDto bookingDto = webClient.get()
                .uri("http://localhost:8082/booking/validateBooking/" + bookingId + "/" + userName)
                .retrieve()
                .bodyToMono(BookingDto.class)
                .block();

        if (bookingDto == null) {
            throw new BookingNotFoundException("Booking with given id not found");
        }

        if (bookingDto.getCheckInStatus()) {
            throw new AlreadyCheckedInException("You have already checked in");
        }

        FlightDto responseEntity = webClient.put()
                .uri("http://localhost:8081/flight/update/" + bookingDto.getFlightId())
                .body(BodyInserters.fromValue(seatNumbers))
                .retrieve()
                .bodyToMono(FlightDto.class).block();

        CheckIn checkIn = new CheckIn();
        checkIn.setCheckInStatus("Success");
        checkIn.setFlightId(bookingDto.getFlightId());
        checkIn.setSeatsBooked(seatNumbers);
        checkIn.setUserName(userName);

        //TODO make a rest api call to booking service to update checkin status

        BookingDto bookingDto1 = webClient.put()
                .uri("http://localhost:8082/booking/updateBookingCheckInStatus/"+bookingId)
                .retrieve()
                .bodyToMono(BookingDto.class)
                .block();

        return repository.save(checkIn);

    }

    @Override
    public CheckIn cancelCheckIn(int flightId) throws BookingNotFoundException {

        Optional<CheckIn> optionalCheckIn = repository.findById(flightId);

        if(optionalCheckIn.isPresent()){

            CheckIn checkIn = optionalCheckIn.get();

            List<String> seatNumbers = checkIn.getSeatsBooked();

            //TODO make a rest api call to flight microservice to restore the seats

            FlightDto updatedFlight = webClient.put()
                    .uri("http://localhost:8081/flight/restoreFlightSeats/"+flightId)
                    .body(BodyInserters.fromValue(seatNumbers))
                    .retrieve()
                    .bodyToMono(FlightDto.class)
                    .block();

            repository.delete(checkIn);

            return checkIn;

        }

        throw new BookingNotFoundException("Check in data with booking id not found");

    }

}
