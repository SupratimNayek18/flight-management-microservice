package capgemini.na.checkIn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import capgemini.na.checkIn.model.CheckIn;

public interface CheckInRepository extends MongoRepository<CheckIn,Integer>{

}