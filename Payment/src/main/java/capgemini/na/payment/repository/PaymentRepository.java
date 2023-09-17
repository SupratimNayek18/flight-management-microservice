package capgemini.na.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import capgemini.na.payment.entity.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Integer> {

	Payment findByBookingId(int bookingId);

}
