package com.olmedo.bookborrowing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book Borrowing API", version = "1.0", description = "Use of the library system"))
public class BookBorrowingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookBorrowingApplication.class, args);
    }

}
