package capgemini.na.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import capgemini.na.payment.controller.PaymentController;
import capgemini.na.payment.entity.Payment;
import capgemini.na.payment.exception.PaymentNotFoundWithIdException;
import capgemini.na.payment.service.PaymentService;

class PaymentControllerTests {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoPayment() throws Exception {
        // Arrange
        String userName = "testUser";
        double amount = 100.0;
        Payment expectedPayment = new Payment();
        when(paymentService.doPayment(userName, amount)).thenReturn(expectedPayment);

        // Act
        Payment result = paymentController.doPayment(userName, amount);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPayment, result);
    }

    @Test
    void testGetPayment() throws PaymentNotFoundWithIdException {
        // Arrange
        String transactionId = "testTransactionId";
        Payment expectedPayment = new Payment();
        when(paymentService.getPayment(transactionId)).thenReturn(expectedPayment);

        // Act
        ResponseEntity<Payment> response = paymentController.getPayment(transactionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPayment, response.getBody());
    }

    @Test
    void testGetPaymentNotFound() throws PaymentNotFoundWithIdException {
        // Arrange
        String transactionId = "nonexistentTransactionId";
        when(paymentService.getPayment(transactionId)).thenThrow(new PaymentNotFoundWithIdException("Payment not found"));

        // Act and Assert
        assertThrows(PaymentNotFoundWithIdException.class, () -> paymentController.getPayment(transactionId));
    }

    @Test
    void testUpdatePayment() throws PaymentNotFoundWithIdException {
        // Arrange
        String transactionId = "testTransactionId";
        Payment paymentToUpdate = new Payment();
        Payment updatedPayment = new Payment();
        when(paymentService.updatePayment(transactionId, paymentToUpdate)).thenReturn(updatedPayment);

        // Act
        ResponseEntity<Payment> response = paymentController.updatePayment(transactionId, paymentToUpdate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPayment, response.getBody());
    }

    @Test
    void testUpdatePaymentNotFound() throws PaymentNotFoundWithIdException {
        // Arrange
        String transactionId = "nonexistentTransactionId";
        Payment paymentToUpdate = new Payment();
        when(paymentService.updatePayment(transactionId, paymentToUpdate))
                .thenThrow(new PaymentNotFoundWithIdException("Payment not found"));

        // Act and Assert
        assertThrows(PaymentNotFoundWithIdException.class,
                () -> paymentController.updatePayment(transactionId, paymentToUpdate));
    }
}

