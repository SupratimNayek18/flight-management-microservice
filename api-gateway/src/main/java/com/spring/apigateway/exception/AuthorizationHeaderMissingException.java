package com.spring.apigateway.exception;

public class AuthorizationHeaderMissingException extends Exception {
    public AuthorizationHeaderMissingException(String jwtTokenMissingInHeader) {
        super(jwtTokenMissingInHeader);
    }
}
