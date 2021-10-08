package com.olmedo.bookborrowing.controller;


import com.olmedo.bookborrowing.entity.Book;
import com.olmedo.bookborrowing.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public List<Book> getBook() {
        List<Book> books = bookService.findAll();
        return books;
    }

    @PostMapping("/")
    public Book create(@RequestBody Book book) throws Exception {
        Book createdBook = bookService.create(book);

        if (createdBook==null) {
            throw new Exception("Genre was not created");
        } else {
            return createdBook;
        }
    }
}
