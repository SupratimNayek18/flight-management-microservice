package capgemini.na.checkIn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralExceptionHandler {
	
	 @ExceptionHandler(value = AlreadyCheckedInException.class)
	    public ResponseEntity<Object> AlreadyCheckedInException(AlreadyCheckedInException exception) {
	        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	    }

}
