package capgemini.na.payment.service;

import org.springframework.stereotype.Service;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;


public interface PaymentService {
	
	boolean  doPayment(int bookingId,String userName,double amount) throws PaymentFailException;
	Payment getPayment(int bookingId);
	Payment updatePayment(int transactionId,Payment payment);

}
