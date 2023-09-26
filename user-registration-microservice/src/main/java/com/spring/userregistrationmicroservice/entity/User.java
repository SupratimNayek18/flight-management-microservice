package com.spring.userregistrationmicroservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userName;
    private String password;
    private String role="ROLE_USER";
    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;

}
