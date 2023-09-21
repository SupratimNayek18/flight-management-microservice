package capgemini.na.checkIn.service;

import java.util.List;

public interface CheckInService {

	boolean checkIn(int flightId,String userName,List<String> seatNumbers);
}
