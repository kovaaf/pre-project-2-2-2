package com.example.exceptions.controllerAdvices;

import org.company.exceptions.NoPersonWithSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoPersonWithSuchIdExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NoPersonWithSuchIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noSuchPersonHandler(NoPersonWithSuchIdException ex) {
        return ex.getMessage();
    }
}
