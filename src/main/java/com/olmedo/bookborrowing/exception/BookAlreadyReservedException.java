package com.olmedo.bookborrowing.exception;

import lombok.Setter;

@Setter
public class BookAlreadyReservedException extends RuntimeException{
    private String message;

    public BookAlreadyReservedException(String message) {
        super(message);
        this.message = message;
    }
}
