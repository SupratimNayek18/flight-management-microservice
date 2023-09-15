package capgemini.na.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import capgemini.na.flight.controller.FlightController;
import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.service.FlightService;

@SpringBootTest
@DataMongoTest
@ActiveProfiles("test")
@Transactional
class FlightApplicationTests {

	@InjectMocks
	FlightController controller;
	
	@Mock
	FlightService service;
	
//	@Test
//	void contextLoads() {
//	}

    @BeforeEach
    void setUp() {
        // Add some sample data before each test
//    	List<Flight> flight=new ArrayList<>();
//        flight.add(new Flight(1, "Flight1", "User1", "Source1", "Destination1", "Arrival1", "Departure1"));
//        flight.add(new Flight(2, "Flight2", "User2", "Source2", "Destination2", "Arrival2", "Departure2"));
//        flight.add(new Flight(3, "Flight3", "User3", "Source3", "Destination3", "Arrival3", "Departure3"));
//        flight.add(new Flight(4, "Flight4", "User4", "Source4", "Destination4", "Arrival4", "Departure4"));

      Flight flight1=new Flight(1, "Flight1", "User1", "Source1", "Destination1", "Arrival1", "Departure1");
      Flight flight2=new Flight(2, "Flight2", "User2", "Source2", "Destination2", "Arrival2", "Departure2");
      Flight flight3=new Flight(3, "Flight3", "User3", "Source3", "Destination3", "Arrival3", "Departure3");
      Flight flight4=new Flight(4, "Flight4", "User4", "Source4", "Destination4", "Arrival4", "Departure4");
      
      service.addFlight(flight1);
      service.addFlight(flight2);
      service.addFlight(flight3);
      service.addFlight(flight4);
      

    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight(5, "Flight5", "User5", "Source5", "Destination5", "Arrival5", "Departure5");

        ResponseEntity<Flight> response = controller.addFlight(flight);

        assertEquals(3, response.getBody().getFlightId());
//        assertEquals("Flight3", response.getBody().getFlightName());
     
    }

   

}
