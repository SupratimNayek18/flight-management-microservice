package capgemini.na.checkIn.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "checkIn")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckIn {

	
	private Integer flightId;
	private String userName;
	private String checkInStatus;
	private List<String> seatsBooked;
}
