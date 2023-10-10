package capgemini.na.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends Exception {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	public FlightNotFoundException(String message) {
		super(message);
	}
}
