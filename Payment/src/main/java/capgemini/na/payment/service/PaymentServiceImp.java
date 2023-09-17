package capgemini.na.payment.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;
import capgemini.na.payment.exception.PaymentNotFoundWithIdException;
import capgemini.na.payment.repository.PaymentRepository;
@Service
public class PaymentServiceImp implements PaymentService{

	@Autowired
	PaymentRepository repository;
	
	@Override
	public boolean doPayment(int bookingId, String userName,double amount) throws PaymentFailException {
		boolean paymentDone=false;
		try {
		Payment payment= new Payment();
		 Random random = new Random();

	        // Generate a random number between 100 and 1000 (inclusive)
	        int randomNumber = random.nextInt(901) + 100;
	        System.out.println(randomNumber);
	        String tractionId=""+bookingId+randomNumber;
	        int id=Integer.parseInt(tractionId);
		payment.setTransactionId(id);
		payment.setBookingId(bookingId);
		payment.setUserName(userName);
		payment.setAmount(amount);
		payment.setTransactionStatus("Payment Successful");
		paymentDone=true;
		repository.save(payment);
		
		}catch (Exception e) {
			// TODO: handle exception
			
			throw new PaymentFailException("Payment Failed of RS "+amount);
			
		}
		return paymentDone;
		
	}

	@Override
	public Payment getPayment(int bookingId) throws PaymentNotFoundWithIdException {
		// TODO Auto-generated method stub
		if(repository.existsByBookingId(bookingId)) {
		Payment payment= repository.findByBookingId(bookingId);
		return payment;
		}else {
			throw new PaymentNotFoundWithIdException("Payment not found with bookingId "+bookingId);
		}
	}

	@Override
	public Payment updatePayment(int transactionId, Payment payment) throws PaymentNotFoundWithIdException {
		// TODO Auto-generated method stub
		if(repository.existsByTransactionId(transactionId)) {
			repository.save(payment);
			return payment;
			}else {
				throw new PaymentNotFoundWithIdException("Payment not found with transactionId "+transactionId);
			}
//		repository.save(payment);
//		return payment;
	}

}
