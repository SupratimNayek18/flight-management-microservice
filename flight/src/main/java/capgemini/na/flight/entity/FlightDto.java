package capgemini.na.flight.entity;

import java.util.List;

public class FlightDto {

	private Integer flightId;
	private String flightName;
	private Integer seats=10;
	private String source;
	private String destination;
	private String date;
	private Double price;
	private String arrival;
	private String departure;
	private List<String> seatNumbers=List.of("A101","A102","A103","A104","A105","A106","A107","A108","A109","A110");

}
