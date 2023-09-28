package capgemini.na.checkIn.controller;

import java.util.Arrays;
import java.util.List;

import capgemini.na.checkIn.exception.BookingNotFoundException;
import capgemini.na.checkIn.model.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import capgemini.na.checkIn.exception.AlreadyCheckedInException;
import capgemini.na.checkIn.service.CheckInService;

@RestController
@RequestMapping("/checkIn")
public class CheckInController {
	
	@Autowired
	CheckInService service;
	
	@GetMapping("/{bookingId}/{userName}")
	public ResponseEntity<CheckIn> checkIn(@PathVariable String bookingId, @PathVariable String userName, @RequestBody List<String> seatNumbers) throws AlreadyCheckedInException, BookingNotFoundException {
		return new ResponseEntity<>(service.checkIn(bookingId, userName, seatNumbers), HttpStatus.OK);
	}

	@PutMapping("/cancelCheckIn/{checkInId}")
	public ResponseEntity<CheckIn> cancelCheckIn(@PathVariable String checkInId) throws BookingNotFoundException {
		return new ResponseEntity<>(service.cancelCheckIn(checkInId),HttpStatus.OK);
	}

}
