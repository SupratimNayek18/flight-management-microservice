package com.spring.bookingmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String transactionId;
    private String bookingId;
    private String userName;
    private double amount;
    private String transactionStatus;

}
