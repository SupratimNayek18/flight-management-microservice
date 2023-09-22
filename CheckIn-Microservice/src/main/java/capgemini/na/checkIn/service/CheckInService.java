package capgemini.na.checkIn.service;

import java.util.List;

import capgemini.na.checkIn.exception.AlreadyCheckedInException;
import capgemini.na.checkIn.exception.BookingNotFoundException;
import capgemini.na.checkIn.model.CheckIn;

public interface CheckInService {

	CheckIn checkIn(int bookingId, String userName, List<String> seatNumbers) throws AlreadyCheckedInException, BookingNotFoundException;
}
