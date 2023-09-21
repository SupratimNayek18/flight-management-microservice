package capgemini.na.flight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.exception.FlightNotFoundException;


public interface FlightService {
	
	void addFlight(Flight flight);
	
	String deleteFlight(int flightId) throws FlightNotFoundException;
	
	List<Flight> viewAllFlight();
	
	List<Flight> searchFlight(String source,String destination) throws FlightNotFoundException;
	
	Flight updateFlight(int flightId,List<String> seatNumbers) throws FlightNotFoundException;
	
	List<Flight> viewFlightsByFlightName(String flightName) throws FlightNotFoundException;

	Flight viewFlightsByFlightId(int flightId) throws FlightNotFoundException;
	
	List<String> getSeatNumbers(int flightId) throws FlightNotFoundException;

	Integer getSeats(int flightId) throws FlightNotFoundException;

}
