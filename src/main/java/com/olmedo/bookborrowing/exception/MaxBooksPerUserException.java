package com.olmedo.bookborrowing.exception;

import lombok.Setter;

@Setter
public class MaxBooksPerUserException extends RuntimeException{
    private String message;

    public MaxBooksPerUserException(String message) {
        super(message);
        this.message = message;
    }
}
