package capgemini.na.checkIn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
	
	private Integer flightId;
	private String flightName;
	private Integer seats;
	private String source;
	private String destination;
	private String date;
	private Double price;
	private String arrival;
	private String departure;

}
