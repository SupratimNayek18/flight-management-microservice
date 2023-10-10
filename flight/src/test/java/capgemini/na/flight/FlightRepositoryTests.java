package capgemini.na.flight;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.repository.FlightRepository;


@SpringBootTest
public class FlightRepositoryTests {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void givenFlightShouldReturnFlightObject() {
        // Given
        Flight flight1 = new Flight(1, "Flight1", 10, "Source1", "Destination1", "2023-01-01", 100.0, "Departure1", "Arrival1",
                List.of("A101"));

        // When
        flightRepository.save(flight1); // Data is saved into the Database
        Flight flight2 = flightRepository.findById(flight1.getFlightId()).orElse(null); // Data is retrieved from the Database

        // Then
        assertNotNull(flight2);
        assertEquals(flight1.getFlightName(), flight2.getFlightName());
    }

    @Test
    public void getAllMustReturnAllFlights() {
        // Given
        Flight flight3 = new Flight(2, "Flight2", 15, "Source2", "Destination2", "2023-01-02", 150.0, "Departure2", "Arrival2",
                List.of("A102"));
        Flight flight4 = new Flight(3, "Flight3", 20, "Source3", "Destination3", "2023-01-03", 200.0, "Departure3", "Arrival3",
                List.of("A103"));
        flightRepository.save(flight3); // Save the Data in the Database
        flightRepository.save(flight4); // Save the Data in the Database

        // When
        List<Flight> flightList = (List<Flight>) flightRepository.findAll();

        // Then
        assertEquals(3, flightList.size());
        assertEquals("Flight3", flightList.get(2).getFlightName());
    }
    
    @Test
    public void givenFlightIdShouldReturnFlightObject() {
        // Given
        Flight flight1 = new Flight(1, "Flight1", 10, "Source1", "Destination1", "2023-01-01", 100.0, "Departure1", "Arrival1",
                List.of("A101"));

        // When
        flightRepository.save(flight1); // Data is saved into the Database
        Optional<Flight> optionalFlight = flightRepository.findById(flight1.getFlightId());

        // Then
        assertTrue(optionalFlight.isPresent());
        Flight retrievedFlight = optionalFlight.get();
        assertEquals(flight1.getFlightName(), retrievedFlight.getFlightName());
    }

    @Test
    public void givenNonExistentFlightIdShouldReturnEmptyOptional() {
        // When
        Optional<Flight> optionalFlight = flightRepository.findById(999);

        // Then
        assertFalse(optionalFlight.isPresent());
    }


    @Test
    public void givenFlightShouldReturnFlightByName() {
        // Given
        Flight flight1 = new Flight(1, "Flight1", 10, "Source1", "Destination1", "2023-01-01", 100.0, "Departure1", "Arrival1",
                List.of("A101"));
        flightRepository.save(flight1);

        // When
        List<Flight> optionalFlight = flightRepository.findByFlightName(flight1.getFlightName());

        // Then
        assertTrue(!optionalFlight.isEmpty());
        assertEquals("Flight1", optionalFlight.get(0).getFlightName());
    }

    @Test
    public void givenNonExistentFlightShouldReturnEmptyOptional() {
        // When
    	List<Flight> optionalFlight = flightRepository.findByFlightName("NonExistentFlight");

        // Then
        assertFalse(!optionalFlight.isEmpty());
    }
}

