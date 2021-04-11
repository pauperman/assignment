package com.mycompany.assignment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidCountryException extends BusinessException {

    public InvalidCountryException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Country.");
    }
}
