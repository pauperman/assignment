package com.mycompany.assignment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LanguageNotFoundException extends BusinessException {

    public LanguageNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Language not found.");
    }
}
