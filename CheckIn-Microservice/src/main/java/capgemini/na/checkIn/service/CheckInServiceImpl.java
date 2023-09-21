package capgemini.na.checkIn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import capgemini.na.checkIn.dto.FlightDto;
import capgemini.na.checkIn.exception.AlreadyCheckedInException;
import capgemini.na.checkIn.model.CheckIn;
import capgemini.na.checkIn.repository.CheckInRepository;

@Service
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	WebClient webClient;
	@Autowired
	CheckInRepository repository;

	@Override
	public boolean checkIn(int flightId, String userName, List<String> seatNumbers) throws AlreadyCheckedInException {

		boolean result = webClient.get()
                .uri("http://localhost:8082/booking/validateBooking/"+flightId+"/"+userName)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
		
		CheckIn userCheckIn=repository.findByUserName(userName);
		if(userCheckIn!=null) {
			throw new AlreadyCheckedInException("User CheckedIn Already");
		}

		if (result) {

			FlightDto responseEntity = webClient.put().uri("http://localhost:8081/flight/update/" + flightId)
					.body(BodyInserters.fromValue(seatNumbers)).retrieve().bodyToMono(FlightDto.class).block();

			CheckIn checkIn = new CheckIn();
			checkIn.setCheckInStatus("CheckIn Successfull");
			checkIn.setFlightId(flightId);
			checkIn.setSeatsBooked(seatNumbers);
			checkIn.setUserName(userName);
			repository.save(checkIn);


			return true;
		}
		return false;
	}

}
