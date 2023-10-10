package capgemini.na.flight.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.exception.FlightNotFoundException;
import capgemini.na.flight.service.FlightService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	
	
	@Autowired
	FlightService service;
	
	
	//Endpoint Adding the flight details.
	@PostMapping("/add")
	public ResponseEntity<Flight> addFlight(@Valid @RequestBody Flight flight) {
		
		service.addFlight(flight);
		
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
	
	
	//Endpoint for Deleting Flight by Id
	@DeleteMapping("/delete/{flightId}")
	public ResponseEntity<String> deleteFlight(@PathVariable int flightId) throws FlightNotFoundException {
		
		String msg=service.deleteFlight(flightId);
		
		return new ResponseEntity<>( msg,HttpStatus.OK);
	}
	
	
	//Endpoint to view all flights
	@GetMapping("/viewAll")
	public ResponseEntity<List<Flight>> viewFlights(){
		
		List<Flight> allFlights=service.viewAllFlight();
		
		return new ResponseEntity<List<Flight>>(allFlights, HttpStatus.OK);
		
	}
	
	
	//Endpoint for searching Flights based on source and destination.
	@GetMapping("/search/{source}/{destination}")
	public ResponseEntity<List<Flight>> search(@PathVariable String source,@PathVariable String destination) throws FlightNotFoundException{
		
		List<Flight> flights= service.searchFlight(source, destination);
		
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}

	
	//Endpoint to Update flight by flightId
	@PutMapping("/update/{flightId}")
	public ResponseEntity<Flight> update(@PathVariable int flightId,@RequestBody ArrayList<String> seatNumbers) throws FlightNotFoundException {

		 Flight flight=service.updateFlight(flightId,seatNumbers);
		 
		 return new ResponseEntity<Flight> (flight,HttpStatus.OK);

	}
	
	//Endpoint  to view flight by FlightName
	@GetMapping("/viewByFlightName/{flightName}")
	public ResponseEntity<List<Flight>> viewByFlightName(@PathVariable String flightName) throws FlightNotFoundException{
		
		List<Flight> viewByName=service.viewFlightsByFlightName(flightName);
		 
		return new ResponseEntity<List<Flight>>(viewByName,HttpStatus.OK);
	}
	
	
	//Endpoint  to view flight by FlightId
	@GetMapping("/viewByFlightId/{flightId}")
	public ResponseEntity<Flight> viewByFlightId(@PathVariable int flightId) throws FlightNotFoundException{
		
		Flight viewById=service.viewFlightsByFlightId(flightId);
		
		return new ResponseEntity<Flight>(viewById,HttpStatus.OK);
	}
	
	
	@GetMapping("/getSeatNumbers/{flightId}")
	public ResponseEntity<Integer> getSeatNumbers(@PathVariable int flightId) throws FlightNotFoundException{
		
		int seats=service.getSeats(flightId);
		
		return new ResponseEntity<Integer>(seats,HttpStatus.OK);
	
	}

	@PutMapping("/restoreFlightSeats/{flightId}")
	public ResponseEntity<Flight> restoreFlightSeats(@PathVariable Integer flightId,@RequestBody List<String> seatNumbers) throws FlightNotFoundException {
		
		return new ResponseEntity<>(service.restoreSeats(flightId,seatNumbers),HttpStatus.OK);
	
	}
	
}
