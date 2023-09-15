package capgemini.na.flight.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	private int flightId;
	@NotBlank(message = "Flight Name is requried")
	private String flightName;
	@NotBlank(message = "User Name is requried")
	private String userName;
	@NotBlank(message = "Source is requried")
	private String source;
	@NotBlank(message = "Destination is requried")
	private String destination;
	@NotBlank(message = "Arrival is requried")
	private String arrival;
	@NotBlank(message = "Departure is requried")
	private String departure;
	
}
