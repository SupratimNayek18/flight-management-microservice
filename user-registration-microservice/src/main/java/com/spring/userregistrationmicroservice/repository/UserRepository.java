package com.spring.userregistrationmicroservice.repository;

import com.spring.userregistrationmicroservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
