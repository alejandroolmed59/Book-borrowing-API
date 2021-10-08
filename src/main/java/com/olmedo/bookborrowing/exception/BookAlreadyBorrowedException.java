package com.olmedo.bookborrowing.exception;

import lombok.Setter;

@Setter
public class BookAlreadyBorrowedException extends RuntimeException{
    private String message;

    public BookAlreadyBorrowedException(String message) {
        super(message);
        this.message = message;
    }
}
