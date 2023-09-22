package capgemini.na.payment.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;
import capgemini.na.payment.exception.PaymentNotFoundWithIdException;
import capgemini.na.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentRepository repository;

    @Override
    public Payment doPayment(String userName, double amount) throws PaymentFailException {
        boolean paymentDone = false;
        try {
            Payment payment = new Payment();
            Random random = new Random();

            // Generate a random number between 100 and 1000 (inclusive)
            int randomNumber = random.nextInt(901) + 100;
            System.out.println(randomNumber);
            String tractionId = "" + randomNumber;
            int id = Integer.parseInt(tractionId);
            payment.setTransactionId(id);
            payment.setUserName(userName);
            payment.setAmount(amount);
            payment.setTransactionStatus("Payment Successful");
            paymentDone = true;
            return repository.save(payment);

        } catch (Exception e) {
            // TODO: handle exception

            throw new PaymentFailException("Payment Failed of RS " + amount);

        }

    }

    @Override
    public Payment getPayment(int transactionId) throws PaymentNotFoundWithIdException {
        // TODO Auto-generated method stub

        Optional<Payment> optionalPayment = repository.findById(transactionId);

        if (optionalPayment.isPresent()) {

            return optionalPayment.get();

        } else {
            throw new PaymentNotFoundWithIdException("Payment not found with transactionId " + transactionId);
        }
    }

    @Override
    public Payment updatePayment(int transactionId, Payment payment) throws PaymentNotFoundWithIdException {
        // TODO Auto-generated method stub
        if (repository.existsByTransactionId(transactionId)) {
            repository.save(payment);
            return payment;
        } else {
            throw new PaymentNotFoundWithIdException("Payment not found with transactionId " + transactionId);
        }
//		repository.save(payment);
//		return payment;
    }

}
