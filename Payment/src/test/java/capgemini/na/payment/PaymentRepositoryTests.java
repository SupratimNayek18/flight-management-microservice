package capgemini.na.payment;

    
    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.junit.jupiter.api.Assertions.assertTrue;

    import java.util.List;
    import java.util.Optional;

    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.junit.jupiter.SpringExtension;

    import capgemini.na.payment.entity.Payment;
    import capgemini.na.payment.repository.PaymentRepository;

    @ExtendWith(SpringExtension.class)
    @SpringBootTest
    public class PaymentRepositoryTests {

        @Autowired
        private PaymentRepository paymentRepository;

        @Test
        public void givenPaymentShouldReturnPaymentObject() {
            // Given
            Payment payment1 = new Payment("1", "Booking1", "User1", 100.0, "Success");

            // When
            paymentRepository.save(payment1); // Data is saved into the Database
            Payment payment2 = paymentRepository.findById(payment1.getTransactionId()).orElse(null); // Data is retrieved from the Database

            // Then
            assertEquals(payment1.getUserName(), payment2.getUserName());
            assertEquals(payment1.getAmount(), payment2.getAmount());
        }

        @Test
        public void getAllMustReturnAllPayments() {
            // Given
            Payment payment3 = new Payment("2", "Booking2", "User2", 150.0, "Success");
            Payment payment4 = new Payment("3", "Booking3", "User3", 200.0, "Success");
            paymentRepository.save(payment3); // Save the Data in the Database
            paymentRepository.save(payment4); // Save the Data in the Database

            // When
            List<Payment> paymentList = (List<Payment>) paymentRepository.findAll();

            // Then
            assertEquals(4, paymentList.size());
            assertEquals("Booking3", paymentList.get(2).getBookingId());
        }

        @Test
        public void givenTransactionIdShouldReturnPaymentObject() {
            // Given
            Payment payment1 = new Payment("1", "Booking1", "User1", 100.0, "Success");

            // When
            paymentRepository.save(payment1); // Data is saved into the Database
            Optional<Payment> optionalPayment = paymentRepository.findById(payment1.getTransactionId());

            // Then
            assertTrue(optionalPayment.isPresent());
            Payment retrievedPayment = optionalPayment.get();
            assertEquals(payment1.getUserName(), retrievedPayment.getUserName());
        }

        @Test
        public void givenNonExistentTransactionIdShouldReturnEmptyOptional() {
            // When
            Optional<Payment> optionalPayment = paymentRepository.findById("NonExistentTransactionId");

            // Then
            assertFalse(optionalPayment.isPresent());
        }


        @Test
        public void testExistsByTransactionId() {
            // Given
            Payment payment1 = new Payment("1", "Booking1", "User1", 100.0, "Success");
            paymentRepository.save(payment1);

            // When
            boolean exists = paymentRepository.existsByTransactionId("1");

            // Then
            assertTrue(exists);
        }

        @Test
        public void testNotExistsByTransactionId() {
            // When
            boolean exists = paymentRepository.existsByTransactionId("NonExistentTransactionId");

            // Then
            assertFalse(exists);
        }
    }


