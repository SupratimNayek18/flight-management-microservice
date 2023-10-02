package capgemini.na.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentFailException;
import capgemini.na.payment.exception.PaymentNotFoundWithIdException;
import capgemini.na.payment.repository.PaymentRepository;
import capgemini.na.payment.service.PaymentServiceImp;

@SpringBootTest
class PaymentApplicationTests {

    @InjectMocks
    private PaymentServiceImp paymentService;

    @Mock
    private PaymentRepository repository;

    @Test
    public void testDoPaymentSuccess() throws PaymentFailException {
        // Arrange
        String userName = "TestUser";
        double amount = 100.0;
        Payment mockPayment = new Payment();
        mockPayment.setTransactionId("1"); 
        mockPayment.setUserName(userName);
        mockPayment.setAmount(100);
        mockPayment.setTransactionStatus("Payment Successful");
        when(repository.save(any(Payment.class))).thenReturn(mockPayment);

        // Act
        Payment result = paymentService.doPayment(userName, amount);

        // Assert
        assertNotNull(result);
        assertEquals(userName, result.getUserName());
        assertEquals(amount, result.getAmount(), 0.001); 
        assertEquals("Payment Successful", result.getTransactionStatus());
    }

    @Test
    public void testGetPaymentSuccess() throws PaymentNotFoundWithIdException {
        // Arrange
        String transactionId = "1";
        Payment mockPayment = new Payment();
        mockPayment.setTransactionId(transactionId);
        when(repository.findById(transactionId)).thenReturn(Optional.of(mockPayment));

        // Act
        Payment result = paymentService.getPayment(transactionId);

        // Assert
        assertNotNull(result);
        assertEquals(transactionId, result.getTransactionId());
    }


    @Test
    public void testGetPaymentNotFound() {
        // Arrange
        String transactionId = "1"; // Change transactionId to String
        when(repository.findById(transactionId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(PaymentNotFoundWithIdException.class, () -> {
            paymentService.getPayment(transactionId);
        });

    }

    @Test
    public void testUpdatePaymentNotFound() {
        // Arrange
        String transactionId = "1"; // Change transactionId to String
        Payment updatedPayment = new Payment();
        updatedPayment.setTransactionId(transactionId);
        when(repository.existsByTransactionId(transactionId)).thenReturn(false);

        // Act and Assert
        assertThrows(PaymentNotFoundWithIdException.class, () -> {
            paymentService.updatePayment(transactionId, updatedPayment);
        });
    }

    @Test
    public void testDoPaymentFailure() {
        // Arrange
        String userName = "TestUser";
        double amount = 100.0;
        when(repository.save(any(Payment.class))).thenThrow(new RuntimeException("Simulating failure"));

        // Act and Assert
        assertThrows(PaymentFailException.class, () -> {
            paymentService.doPayment(userName, amount);
        });
    }
}
