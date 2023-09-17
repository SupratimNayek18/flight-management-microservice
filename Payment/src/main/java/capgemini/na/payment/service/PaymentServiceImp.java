package capgemini.na.payment.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;
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
	public Payment getPayment(int bookingId) {
		// TODO Auto-generated method stub
		return repository.findByBookingId(bookingId);
	}

	@Override
	public Payment updatePayment(int transactionId, Payment payment) {
		// TODO Auto-generated method stub
		repository.save(payment);
		return payment;
	}

}
