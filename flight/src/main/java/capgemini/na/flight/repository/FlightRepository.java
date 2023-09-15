package capgemini.na.flight.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import capgemini.na.flight.entity.Flight;


public interface FlightRepository extends MongoRepository<Flight, Integer>{

	List<Flight> findByFlightName(String flightName);

}
