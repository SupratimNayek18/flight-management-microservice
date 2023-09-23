package capgemini.na.flight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.exception.FlightNotFoundException;
import capgemini.na.flight.repository.FlightRepository;
@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository repository;
	
	
	@Override
	public Flight addFlight(Flight flight) {
		repository.save(flight);
		return flight;
	}

	@Override
	public String deleteFlight(int flightId) throws FlightNotFoundException {
			
	    if (repository.existsById(flightId)) {
            repository.deleteById(flightId);
            return "Flight Deleted Successfully...!";
        } else {
            throw new FlightNotFoundException("Flight with ID " + flightId + " not found");
        }
	
			
	}

	@Override
	public List<Flight> viewAllFlight() {
		
		List<Flight> flight= new ArrayList<Flight>();
		repository.findAll().forEach(flightDetails->flight.add(flightDetails));
		return flight;
	}

	@Override
	public List<Flight> searchFlight(String source, String destination) throws FlightNotFoundException {
		
		List<Flight> flight= new ArrayList<Flight>();
		
		repository.findAll().forEach(flightDetails->flight.add(flightDetails));
		
		List<Flight> matchedFlights=new ArrayList<Flight>();
		
		matchedFlights=flight.stream().filter(f->(f.getSource().equals(source)&&f.getDestination().equals(destination))).collect(Collectors.toList());
		
//				flight.stream().filter(f->(f.getSource()==source&&f.getDestination()==destination)).forEach(f->matchedFlights.add(f));
//		System.out.println(matchedFlights);
		
		   if (matchedFlights.isEmpty()) {
	            throw new FlightNotFoundException("No flights found for source: " + source + " and destination: " + destination);
	        }
		   
		return matchedFlights;
	}

	@Override
	public Flight updateFlight(int flightId,List<String> seatNumbers) throws FlightNotFoundException {
//		repository.save(flight);
		System.out.println(seatNumbers);
		
		Optional<Flight> optionalFlight=repository.findById(flightId);
		
		if(optionalFlight.isPresent()) {
			
			Flight flight=optionalFlight.get();
			
			   int seats=flight.getSeats();
			   
			   seats-=seatNumbers.size();
			 
			   List<String> fetchedSeatNumbers =flight.getSeatNumbers();
			   
			   for(String seat:seatNumbers) {
				   
				   if(fetchedSeatNumbers.contains(seat)) {
					   
					   fetchedSeatNumbers.remove(seat);
				   }
			   }

			   flight.setSeats(seats);
			   flight.setSeatNumbers(fetchedSeatNumbers);
			   
			   repository.save(flight);
			   
			   return flight;
	        } 
			   else {
	            throw new FlightNotFoundException("Flight with ID " + flightId + " not found. Cannot update.");
	        }
		
		
	}

	@Override
	public List<Flight> viewFlightsByFlightName(String flightName) throws FlightNotFoundException  {
		

		 List<Flight> matchedFlights = repository.findByFlightName(flightName);

	        if (matchedFlights.isEmpty()) {
	        	
	            throw new FlightNotFoundException("No flights found with the name: " + flightName);
	        }

	        return matchedFlights;
	}

	@Override
	public Flight viewFlightsByFlightId(int flightId) throws FlightNotFoundException {
		
		 Optional<Flight> matchedFlights = repository.findById(flightId);

	        if (matchedFlights.isEmpty()) {
	        	
	            throw new FlightNotFoundException("No flights found with the Id: " + flightId);
	        }

	        return matchedFlights.get();
	}

	@Override
	public List<String> getSeatNumbers(int flightId) throws FlightNotFoundException {
		
		Flight matchedFlights=repository.findById(flightId).get();
		
		if (matchedFlights==null) {
			
            throw new FlightNotFoundException("No flights found with the Id: " + flightId);
        }
		
		
		return matchedFlights.getSeatNumbers();
		
		
	}

	@Override
	public Integer getSeats(int flightId) throws FlightNotFoundException {
		
		Flight matchedFlights=repository.findById(flightId).get();
		
		if (matchedFlights==null) {
			
            throw new FlightNotFoundException("No flights found with the Id: " + flightId);
        }
		
		
		return matchedFlights.getSeats();

	}

}
