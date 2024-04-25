package com.example.exceptions.controllerAdvices;

import com.example.exceptions.NoSuchSortingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoSuchSortingExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NoSuchSortingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noSuchSortingHandler(NoSuchSortingException ex) {
        return ex.getMessage();
    }
}
