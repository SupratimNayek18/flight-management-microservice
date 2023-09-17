package capgemini.na.flight.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "flight")
public class Flight {

	@Id
	private Integer flightId;
	@NotBlank(message = "Flight Name is requried")
	private String flightName;
	@NotBlank(message = "User Name is requried")
	private String userName;
	@PositiveOrZero(message="Seates must be positive")
	private Integer seats;
	@NotBlank(message = "Source is requried")
	private String source;
	@NotBlank(message = "Destination is requried")
	private String destination;
	@NotBlank(message = "Date is Requried")
	private String date;
	@NotBlank(message = "Price is Requried")
	private Double price;
	@NotBlank(message = "Arrival is requried")
	private String arrival;
	@NotBlank(message = "Departure is requried")
	private String departure;
	
}
