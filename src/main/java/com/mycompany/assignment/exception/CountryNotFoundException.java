package com.mycompany.assignment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CountryNotFoundException extends BusinessException {

    public CountryNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Country not found.");
    }
}
