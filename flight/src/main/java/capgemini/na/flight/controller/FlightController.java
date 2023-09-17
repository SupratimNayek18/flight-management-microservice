package capgemini.na.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.exception.FlightNotFoundException;
import capgemini.na.flight.service.FlightService;
import jakarta.validation.Valid;
//@RestControllerAdvice
@RestController
@RequestMapping("/flight")
public class FlightController {
	
	
	
	@Autowired
	FlightService service;
	
	@PostMapping("/add")
	public ResponseEntity<Flight> addFlight(@Valid @RequestBody Flight flight) {
		service.addFlight(flight);
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
//	@ExceptionHandler(FlightNotFoundException.class)
	@DeleteMapping("/delete/{flightId}")
	public ResponseEntity<String> deleteFlight(@PathVariable int flightId) throws FlightNotFoundException {
		String msg=service.deleteFlight(flightId);
		return new ResponseEntity<>( msg,HttpStatus.OK);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Flight>> viewFlights(){
		List<Flight> allFlights=service.viewAllFlight();
		return new ResponseEntity<List<Flight>>(allFlights, HttpStatus.OK);
	}
	
	@GetMapping("/search/{source}/{destination}")
	public ResponseEntity<List<Flight>> search(@PathVariable String source,@PathVariable String destination) throws FlightNotFoundException{
		List<Flight> flights= service.searchFlight(source, destination);
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}

	@PutMapping("/update/{flightId}")
	public ResponseEntity<Flight> update(@PathVariable int flightId,@Valid @RequestBody Flight flight) throws FlightNotFoundException {
		 service.updateFlight(flightId,flight);
		 return new ResponseEntity<Flight> (flight,HttpStatus.OK);
	}
	
	@GetMapping("/viewByFlightName/{flightName}")
	public ResponseEntity<List<Flight>> viewByFlightName(@PathVariable String flightName) throws FlightNotFoundException{
		List<Flight> viewByName=service.viewFlightsByFlightName(flightName);
		 
		return new ResponseEntity<List<Flight>>(viewByName,HttpStatus.OK);
	}
	
	@GetMapping("/viewByFlightId/{flightId}")
	public ResponseEntity<Flight> viewByFlightId(@PathVariable int flightId) throws FlightNotFoundException{
		Flight viewById=service.viewFlightsByFlightId(flightId);
		return new ResponseEntity<Flight>(viewById,HttpStatus.OK);
	}
}