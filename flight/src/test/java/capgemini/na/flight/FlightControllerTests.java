package capgemini.na.flight;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import capgemini.na.flight.controller.FlightController;
import capgemini.na.flight.entity.Flight;
import capgemini.na.flight.exception.FlightNotFoundException;
import capgemini.na.flight.service.FlightService;

public class FlightControllerTests {

    @InjectMocks
    private FlightController controller;

    @Mock
    private FlightService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFlight() {
        Flight flightToAdd = new Flight(1, "Flight1",10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110"));
        when(service.addFlight(flightToAdd)).thenReturn(flightToAdd);

        ResponseEntity<Flight> response = controller.addFlight(flightToAdd);

        verify(service, times(1)).addFlight(flightToAdd);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flightToAdd, response.getBody());
    }

    @Test
    public void testDeleteFlight() throws FlightNotFoundException {
        int flightIdToDelete = 1;
        when(service.deleteFlight(eq(flightIdToDelete))).thenReturn("Flight deleted successfully");

        ResponseEntity<String> response = controller.deleteFlight(flightIdToDelete);

        verify(service, times(1)).deleteFlight(eq(flightIdToDelete));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Flight deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteFlightFlightNotFoundException() throws FlightNotFoundException {
        int flightIdToDelete = 1;
        when(service.deleteFlight(eq(flightIdToDelete))).thenThrow(new FlightNotFoundException("Flight not found"));

        assertThrows(FlightNotFoundException.class, () -> controller.deleteFlight(flightIdToDelete));
    }

    @Test
    public void testViewFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(2, "Flight2",10,"Mumbai","Bangalore","24/09/2023",6000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flights.add(new Flight(1, "Flight1",10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        when(service.viewAllFlight()).thenReturn(flights);

        ResponseEntity<List<Flight>> response = controller.viewFlights();

        verify(service, times(1)).viewAllFlight();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flights, response.getBody());
    }

    @Test
    public void testSearch() throws FlightNotFoundException {
        String source = "Hyderabad";
        String destination = "Bangalore";
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(2, "Flight2",10,"Mumbai","Bangalore","24/09/2023",6000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flights.add(new Flight(1, "Flight1",10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        when(service.searchFlight(eq(source), eq(destination))).thenReturn(flights);

        ResponseEntity<List<Flight>> response = controller.search(source, destination);

        verify(service, times(1)).searchFlight(eq(source), eq(destination));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flights, response.getBody());
    }

    @Test
    public void testSearchFlightNotFoundException() throws FlightNotFoundException {
        String source = "Mumbai";
        String destination = "Hyderabad";
        when(service.searchFlight(eq(source), eq(destination))).thenThrow(new FlightNotFoundException("Flights not found"));

        assertThrows(FlightNotFoundException.class, () -> controller.search(source, destination));
    }

    @Test
    public void testUpdate() throws FlightNotFoundException {
        int flightId = 1;
        ArrayList<String> seatNumbers = new ArrayList<>();
        seatNumbers.add("A103");
        seatNumbers.add("A104");
        Flight updatedFlight = new Flight(1, "Flight1",10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110"));
        
        when(service.updateFlight(eq(flightId), eq(seatNumbers))).thenReturn(updatedFlight);

        ResponseEntity<Flight> response = controller.update(flightId, seatNumbers);

        verify(service, times(1)).updateFlight(eq(flightId), eq(seatNumbers));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFlight, response.getBody());
    }

    @Test
    public void testUpdateFlightNotFoundException() throws FlightNotFoundException {
        int flightId = 5;
        ArrayList<String> seatNumbers = new ArrayList<>();
        when(service.updateFlight(eq(flightId), eq(seatNumbers))).thenThrow(new FlightNotFoundException("Flight not found"));

        assertThrows(FlightNotFoundException.class, () -> controller.update(flightId, seatNumbers));
    }

    @Test
    public void testViewByFlightName() throws FlightNotFoundException {
        String flightName = "Flight1";
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(2, "Flight2",10,"Mumbai","Bangalore","24/09/2023",6000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        flights.add(new Flight(1, "Flight1",10,"Hyderabad","Bangalore","23/09/2023",5000.00,"1:00PM","4:00PM",List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110")));
        when(service.viewFlightsByFlightName(eq(flightName))).thenReturn(flights);

        ResponseEntity<List<Flight>> response = controller.viewByFlightName(flightName);

        verify(service, times(1)).viewFlightsByFlightName(eq(flightName));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flights, response.getBody());
    }

    @Test
    public void testViewByFlightNameFlightNotFoundException() throws FlightNotFoundException {
        String flightName = "Flight5";
        when(service.viewFlightsByFlightName(eq(flightName))).thenThrow(new FlightNotFoundException("Flight not found"));

        assertThrows(FlightNotFoundException.class, () -> controller.viewByFlightName(flightName));
    }

    @Test
    public void testViewByFlightId() throws FlightNotFoundException {
        int flightId = 1;
        Flight flight = new Flight();
        when(service.viewFlightsByFlightId(eq(flightId))).thenReturn(flight);

        ResponseEntity<Flight> response = controller.viewByFlightId(flightId);

        verify(service, times(1)).viewFlightsByFlightId(eq(flightId));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flight, response.getBody());
    }

    @Test
    public void testViewByFlightIdFlightNotFoundException() throws FlightNotFoundException {
        int flightId = 1;
        when(service.viewFlightsByFlightId(eq(flightId))).thenThrow(new FlightNotFoundException("Flight not found"));

        assertThrows(FlightNotFoundException.class, () -> controller.viewByFlightId(flightId));
    }

    @Test
    public void testGetSeatNumbers() throws FlightNotFoundException {
        int flightId = 1;
        int seats = 10;
        when(service.getSeats(eq(flightId))).thenReturn(seats);

        ResponseEntity<Integer> response = controller.getSeatNumbers(flightId);

        verify(service, times(1)).getSeats(eq(flightId));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(seats, response.getBody());
    }

    @Test
    public void testGetSeatNumbersFlightNotFoundException() throws FlightNotFoundException {
        int flightId = 1;
        when(service.getSeats(eq(flightId))).thenThrow(new FlightNotFoundException("Flight not found"));

        assertThrows(FlightNotFoundException.class, () -> controller.getSeatNumbers(flightId));
    }

    @Test
    public void testRestoreFlightSeats() throws FlightNotFoundException {
        int flightId = 1;
        List<String> seatNumbers = new ArrayList<>();
        Flight restoredFlight = new Flight();
        when(service.restoreSeats(eq(flightId), eq(seatNumbers))).thenReturn(restoredFlight);

        ResponseEntity<Flight> response = controller.restoreFlightSeats(flightId, seatNumbers);

        verify(service, times(1)).restoreSeats(eq(flightId), eq(seatNumbers));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restoredFlight, response.getBody());
    }


}
