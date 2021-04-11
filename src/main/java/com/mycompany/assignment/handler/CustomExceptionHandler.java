package com.mycompany.assignment.handler;

import com.mycompany.assignment.exception.BusinessException;
import com.mycompany.assignment.model.output.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorMessage> handleBusinessExceptions(BusinessException ex, WebRequest request) {
        return new ResponseEntity(new ErrorMessage(ex.getMessage()), ex.getHttpStatus());
    }
}
