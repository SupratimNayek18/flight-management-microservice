package capgemini.na.payment.service;

import org.springframework.stereotype.Service;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;
import capgemini.na.payment.exception.PaymentNotFoundWithIdException;


public interface PaymentService {
	
	Payment  doPayment(String userName,double amount) throws PaymentFailException;
	Payment getPayment(int transactionId) throws PaymentNotFoundWithIdException;
	Payment updatePayment(int transactionId,Payment payment) throws PaymentNotFoundWithIdException;

}
