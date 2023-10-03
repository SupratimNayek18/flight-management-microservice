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
	
	
	
	//Method to add flight data into database.
	@Override
	public Flight addFlight(Flight flight) {
		
		repository.save(flight);
		
		return flight;
	}

	//Method to delete flight data from database by flightId
	@Override
	public String deleteFlight(int flightId) throws FlightNotFoundException {
			
		//checking if flight with given Id is exists or not.
	    if (repository.existsById(flightId)) {
	    	
            repository.deleteById(flightId);
            
            return "Flight Deleted Successfully...!";
        } else {
        	
            throw new FlightNotFoundException("Flight with ID " + flightId + " not found");
        }
			
	}

	//Method to view all flights 
	@Override
	public List<Flight> viewAllFlight() {
		
		List<Flight> flight= new ArrayList<Flight>();
		//getting all the flight data and adding into the list.
		repository.findAll().forEach(flightDetails->flight.add(flightDetails));
		
		return flight;
	}

	
	
	//Method to search flights based on source and destination.
	@Override
	public List<Flight> searchFlight(String source, String destination) throws FlightNotFoundException {
		
		List<Flight> flight= new ArrayList<Flight>();
		//getting all the flight details.
		repository.findAll().forEach(flightDetails->flight.add(flightDetails));
		
		List<Flight> matchedFlights=new ArrayList<Flight>();
		//matching the flight data of given source and destination.
		matchedFlights=flight.stream().filter(f->(f.getSource().equals(source)&&f.getDestination().equals(destination))).collect(Collectors.toList());
		
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

	//method to view flights by flightName.                           
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

	@Override
	public Flight restoreSeats(int flightId, List<String> seatNumbers) throws FlightNotFoundException {

		Optional<Flight> optionalFlight = repository.findById(flightId);

		if(optionalFlight.isPresent()){

			Flight flight = optionalFlight.get();

			//Getting seatNumbers and seat count from Flight db
			List<String> fetchedSeats = flight.getSeatNumbers();
			Integer seatCount = flight.getSeats();

			//Adding cancelled seats back into the list
			fetchedSeats.addAll(seatNumbers);

			//Setting new value of restored seat count and seat numbers
			flight.setSeats(seatCount+seatNumbers.size());
			flight.setSeatNumbers(fetchedSeats);

			return repository.save(flight);

		}

		throw new FlightNotFoundException("Flight with id "+flightId+" not found");

	}

}
