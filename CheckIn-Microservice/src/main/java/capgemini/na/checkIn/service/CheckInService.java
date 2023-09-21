package capgemini.na.checkIn.service;

import java.util.List;

import capgemini.na.checkIn.exception.AlreadyCheckedInException;
import capgemini.na.checkIn.exception.BookingNotFoundException;

public interface CheckInService {

	boolean checkIn(int bookingId,String userName,List<String> seatNumbers) throws AlreadyCheckedInException, BookingNotFoundException;
}
