package capgemini.na.checkIn.service;

import java.util.List;

import capgemini.na.checkIn.exception.AlreadyCheckedInException;

public interface CheckInService {

	boolean checkIn(int flightId,String userName,List<String> seatNumbers) throws AlreadyCheckedInException;
}
