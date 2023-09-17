package capgemini.na.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;
import capgemini.na.payment.repository.PaymentRepository;
import capgemini.na.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	@GetMapping("/doPayment/{bookingId}/{userName}/{amount}")
	public boolean doPayment(@PathVariable int bookingId,@PathVariable String userName,@PathVariable double amount) throws Exception {
		return service.doPayment(bookingId, userName,amount);
	}
	@GetMapping("/getByBookingId/{bookingId}")
	public ResponseEntity<Payment> getPayment(@PathVariable int bookingId) {
		Payment payment= service.getPayment(bookingId);
		return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	
	@PutMapping("/update/{transactionId}")
	public ResponseEntity<Payment> updatPayment(@PathVariable int transactionId,@RequestBody Payment payment) {
		Payment payment2= service.updatePayment(transactionId, payment);
		return new ResponseEntity<Payment>(payment2,HttpStatus.OK);
		
	}

}
