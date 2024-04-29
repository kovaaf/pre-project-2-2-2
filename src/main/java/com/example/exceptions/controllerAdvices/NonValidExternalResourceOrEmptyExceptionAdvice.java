package com.example.exceptions.controllerAdvices;

import org.company.exceptions.NonValidExternalResourceOrEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NonValidExternalResourceOrEmptyExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NonValidExternalResourceOrEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noSuchPersonHandler(NonValidExternalResourceOrEmptyException ex) {
        return ex.getMessage();
    }
}
