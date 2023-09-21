package capgemini.na.checkIn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capgemini.na.checkIn.service.CheckInService;

@RestController
@RequestMapping("/checkIn")
public class CheckInController {
	
	@Autowired
	CheckInService service;
	
	@GetMapping("/{flightId}/{userName}")
	public boolean checkIn(@PathVariable int flightId,@PathVariable String userName,@RequestBody List<String> seatNumbers) {
		return service.checkIn(flightId, userName, seatNumbers);
	}

}
