package com.example.exceptions;

public class NoSuchSortingException extends RuntimeException {
    public NoSuchSortingException(String sortBy) {
        super("No such sorting: " + sortBy);
    }
}
