package capgemini.na.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PaymentFailException extends Exception {
	
	public PaymentFailException(String message) {
	
		super(message);
	}
}
