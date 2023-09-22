package capgemini.na.checkIn.controller;

import java.util.Arrays;
import java.util.List;

import capgemini.na.checkIn.exception.BookingNotFoundException;
import capgemini.na.checkIn.model.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capgemini.na.checkIn.exception.AlreadyCheckedInException;
import capgemini.na.checkIn.service.CheckInService;

@RestController
@RequestMapping("/checkIn")
public class CheckInController {
	
	@Autowired
	CheckInService service;
	
	@GetMapping("/{bookingId}/{userName}")
	public CheckIn checkIn(@PathVariable int bookingId, @PathVariable String userName, @RequestBody List<String> seatNumbers) throws AlreadyCheckedInException, BookingNotFoundException {
		return service.checkIn(bookingId, userName, seatNumbers);
	}

}
