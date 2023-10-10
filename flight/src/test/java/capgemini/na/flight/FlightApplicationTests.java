package capgemini.na.flight;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.exception.FlightNotFoundException;
import capgemini.na.flight.repository.FlightRepository;
import capgemini.na.flight.service.FlightServiceImpl;

@SpringBootTest
class FlightServiceImplTests {

    @InjectMocks
    private FlightServiceImpl flightService;

    @Mock
    private FlightRepository flightRepository;

//    @BeforeEach
//    void init() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        flight.setFlightId(1);
        flight.setFlightName("TestFlight");

        when(flightRepository.save(flight)).thenReturn(flight);

        Flight addedFlight = flightService.addFlight(flight);

        assertEquals(flight.getFlightName(), addedFlight.getFlightName());
    }

    @Test
    void testDeleteFlight() {
        int flightId = 1;
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        flight.setFlightName("TestFlight");

        when(flightRepository.existsById(flightId)).thenReturn(true);
        doNothing().when(flightRepository).deleteById(flightId);

        assertDoesNotThrow(() -> flightService.deleteFlight(flightId));
    }

    @Test
    void testDeleteFlight_NotFound() {
        int flightId = 1;

        when(flightRepository.existsById(flightId)).thenReturn(false);

        assertThrows(FlightNotFoundException.class, () -> flightService.deleteFlight(flightId));
    }

    @Test
    void testViewAllFlight() {
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight(2, "Flight2",10,"Mumbai","Bangalore","24/09/2023",6000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flightList.add(new Flight(1, "Flight1",10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));

        when(flightRepository.findAll()).thenReturn(flightList);

        List<Flight> retrievedFlights = flightService.viewAllFlight();

        assertEquals(2, retrievedFlights.size());
        assertEquals("Flight1", retrievedFlights.get(0).getFlightName());
        assertEquals("Flight2", retrievedFlights.get(1).getFlightName());
    }

    @Test
    void testSearchFlight() throws FlightNotFoundException  {

        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight(1, "Flight1", 10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flightList.add(new Flight(2, "Flight2",10,"Hyderabad","Bangalore","24/09/2023",6000.00,"4:00PM","6:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flightList.add(new Flight(3, "Flight3",10,"Mumbai","Bangalore","24/09/2023",6000.00,"4:00PM","6:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));

        when(flightRepository.findAll()).thenReturn(flightList);

        List<Flight> searchedFlights = flightService.searchFlight("Hyderabad", "Bangalore");

        assertEquals(2, searchedFlights.size());
        assertEquals("Hyderabad", searchedFlights.get(0).getSource());
        assertEquals("Bangalore", searchedFlights.get(0).getDestination());
        assertEquals("Hyderabad", searchedFlights.get(1).getSource());
        assertEquals("Bangalore", searchedFlights.get(1).getDestination());
    }

    @Test
    void testSearchFlight_NotFound() {

        List<Flight> flightList = new ArrayList<>();

        when(flightRepository.findAll()).thenReturn(flightList);

        assertThrows(FlightNotFoundException.class, () -> flightService.searchFlight("Hyderabad", "Bangalore"));
    }
//
    @Test
    void testUpdateFlight() throws FlightNotFoundException {
        int flightId = 1;
        List<String> immutableList = List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110");

     // Convert the immutable list to a mutable ArrayList
     List<String> mutableList = new ArrayList<>(immutableList);
        Flight flight = new Flight(flightId, "Flight1", 10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",mutableList);

        List<String> seatNumbers = new ArrayList<>();
        seatNumbers.add("A101");

        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        Flight updatedFlight = flightService.updateFlight(flightId, seatNumbers);

        assertEquals(9, updatedFlight.getSeats());
        assertFalse(updatedFlight.getSeatNumbers().contains("A101"));
    }

    @Test
    void testUpdateFlight_NotFound() {
        int flightId = 1;
        List<String> seatNumbers = new ArrayList<>();
        seatNumbers.add("A101");

        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> flightService.updateFlight(flightId, seatNumbers));
    }

    // Add test cases for other service methods like viewByFlightName, viewByFlightId, getSeatNumbers, and getSeats.
    
    @Test
    void testViewFlightsByFlightName() throws FlightNotFoundException {
//        String flightName = "Flight1";
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight(1, "Flight1", 10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flightList.add(new Flight(2, "Flight2", 10,"Hyderabad","Bangalore","23/09/2023",6000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));

        when(flightRepository.findByFlightName("Flight1")).thenReturn(flightList);

        List<Flight> retrievedFlights = flightService.viewFlightsByFlightName("Flight1");

        assertEquals(2, retrievedFlights.size());
        assertEquals("Flight1", retrievedFlights.get(0).getFlightName());
        assertEquals("Flight2", retrievedFlights.get(1).getFlightName());
    }

    @Test
    void testViewFlightsByFlightName_NotFound() {
        String flightName = "NonExistentFlight";

        when(flightRepository.findByFlightName(flightName)).thenReturn(new ArrayList<>());

        assertThrows(FlightNotFoundException.class, () -> flightService.viewFlightsByFlightName(flightName));
    }

    @Test
    void testViewFlightsByFlightId() throws FlightNotFoundException {
        int flightId = 1;
        Flight flight = new Flight(flightId, "Flight1", 10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110"));

        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        Flight retrievedFlight = flightService.viewFlightsByFlightId(flightId);

        assertNotNull(retrievedFlight);
        assertEquals(flightId, retrievedFlight.getFlightId());
        assertEquals("Flight1", retrievedFlight.getFlightName());
    }

    @Test
    void testViewFlightsByFlightId_NotFound() {
        int flightId = 1;

        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> flightService.viewFlightsByFlightId(flightId));
    }

    @Test
    void testGetSeatNumbers() throws FlightNotFoundException {
        int flightId = 1;
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        flight.setSeatNumbers(List.of("A101", "A102","A103","A104","A105","A106","A107","A108","A109","A110"));

        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        List<String> seatNumbers = flightService.getSeatNumbers(flightId);

        assertNotNull(seatNumbers);
        assertEquals(10, seatNumbers.size());
        assertTrue(seatNumbers.contains("A101"));
        assertTrue(seatNumbers.contains("A102"));
        assertTrue(seatNumbers.contains("A103"));
        assertTrue(seatNumbers.contains("A104"));
        assertTrue(seatNumbers.contains("A105"));
        assertTrue(seatNumbers.contains("A106"));
        assertTrue(seatNumbers.contains("A107"));
        assertTrue(seatNumbers.contains("A108"));
        assertTrue(seatNumbers.contains("A109"));
        assertTrue(seatNumbers.contains("A110"));
    }

  

    @Test
    void testGetSeats() throws FlightNotFoundException {
        int flightId = 1;
        Flight flight = new Flight();
        flight.setFlightId(flightId);
        flight.setSeats(10);

        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        int seats = flightService.getSeats(flightId);

        assertEquals(10, seats);
    }
    
    
}

