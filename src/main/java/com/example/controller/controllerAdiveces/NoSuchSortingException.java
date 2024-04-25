package com.example.controller.controllerAdiveces;

public class NoSuchSortingException extends RuntimeException {
    public NoSuchSortingException(String sortBy) {
        super("No such sorting: " + sortBy);
    }
}
