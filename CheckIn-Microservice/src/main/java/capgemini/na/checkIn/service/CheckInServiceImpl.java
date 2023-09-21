package capgemini.na.checkIn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import capgemini.na.checkIn.dto.FlightDto;
@Service
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	WebClient webClient;
	
	@Override
	public boolean checkIn(int flightId, String userName,List<String> seatNumbers) {
		
		boolean result = webClient.get()
                .uri("http://localhost:8082/booking/validateBooking/"+flightId+"/"+userName)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
		if(result) {
			
			FlightDto updateFlight= webClient.put()
	                .uri("http://localhost:8081/flight/update/"+flightId)
	                .retrieve()
	                .bodyToMono(FlightDto.class)
	                .block();
			
			
			FlightDto flightDto= webClient.put()
	                .uri("http://localhost:8081/flight/update/"+flightId)
	                .retrieve()
	                .bodyToMono(FlightDto.class)
	                .block();
			
		}
		return false;
	}

}
