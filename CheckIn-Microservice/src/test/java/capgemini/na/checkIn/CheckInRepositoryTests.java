package capgemini.na.checkIn;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import capgemini.na.checkIn.model.CheckIn;
import capgemini.na.checkIn.repository.CheckInRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CheckInRepositoryTests {

    @Autowired
    private CheckInRepository checkInRepository;

    @Test
    public void givenCheckInShouldReturnCheckInObject() {
        // Given
        CheckIn checkIn1 = new CheckIn("1", 1, "user1", "Success", List.of("A101"));

        // When
        checkInRepository.save(checkIn1); // Data is saved into the Database
        CheckIn checkIn2 = checkInRepository.findById(checkIn1.getCheckInId()).orElse(null); // Data is retrieved from the Database

        // Then
        assertAll(
            () -> assertNotNull(checkIn2),
            () -> assertEquals(checkIn1.getUserName(), checkIn2.getUserName())
        );
    }

    @Test
    public void getAllMustReturnAllCheckIns() {
        // Given
        CheckIn checkIn3 = new CheckIn("2", 2, "user2", "Success", List.of("A102"));
        CheckIn checkIn4 = new CheckIn("3", 3, "user3", "Success", List.of("A103"));
        checkInRepository.save(checkIn3); // Save the Data in the Database
        checkInRepository.save(checkIn4); // Save the Data in the Database

        // When
        List<CheckIn> checkInList = (List<CheckIn>) checkInRepository.findAll();

        // Then
        assertAll(
            () -> assertEquals(2, checkInList.size()),
            () -> assertEquals("user3", checkInList.get(1).getUserName())
        );
    }
    
    @Test
    public void givenCheckInIdShouldReturnCheckInObject() {
        // Given
        CheckIn checkIn1 = new CheckIn("1", 1, "user1", "Success", List.of("A101"));

        // When
        checkInRepository.save(checkIn1); // Data is saved into the Database
        Optional<CheckIn> optionalCheckIn = checkInRepository.findById(checkIn1.getCheckInId());

        // Then
        assertAll(
            () -> assertTrue(optionalCheckIn.isPresent()),
            () -> {
                CheckIn retrievedCheckIn = optionalCheckIn.get();
                assertEquals(checkIn1.getUserName(), retrievedCheckIn.getUserName());
            }
        );
    }

    @Test
    public void givenNonExistentCheckInIdShouldReturnEmptyOptional() {
        // When
        Optional<CheckIn> optionalCheckIn = checkInRepository.findById("NonExistentCheckInId");

        // Then
        assertFalse(optionalCheckIn.isPresent());
    }


    @Test
    public void givenCheckInShouldReturnCheckInByUserName() {
        // Given
        CheckIn checkIn1 = new CheckIn("1", 1, "user1", "Success", List.of("A101"));
        checkInRepository.save(checkIn1);

        // When
        CheckIn optionalCheckIn = checkInRepository.findByUserName("user1");

        // Then
        assertNotNull(optionalCheckIn);
        assertEquals("user1", optionalCheckIn.getUserName());
    }

    @Test
    public void givenNonExistentCheckInShouldReturnNull() {
        // When
        CheckIn optionalCheckIn = checkInRepository.findByUserName("NonExistentUser");

        // Then
        assertNull(optionalCheckIn);
    }

    @AfterEach
    public void cleanup() {
        checkInRepository.deleteAll();
    }
}

