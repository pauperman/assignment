package com.mycompany.assignment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CityNotFoundException extends BusinessException {

    public CityNotFoundException() {
        super(HttpStatus.NOT_FOUND, "City not found.");
    }
}
