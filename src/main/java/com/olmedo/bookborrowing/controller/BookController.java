package com.olmedo.bookborrowing.controller;


import com.olmedo.bookborrowing.entity.Book;
import com.olmedo.bookborrowing.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{ISBN}")
    public ResponseEntity findByISBN(@PathVariable("ISBN") String ISBN){
        Book book = bookService.findByIsbn(ISBN);
        System.out.println(book.toString());
        //Boolean ocupado = book.getBorrows().stream().anyMatch(borrowing -> borrowing.getDelivered()==false);
        return new ResponseEntity("ocupado= ", HttpStatus.OK );
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Book newValues, @PathVariable String id) throws Exception {
        Book found = bookService.findByIsbn(id);

        if (found == null) {
            throw new Exception("Book not found with id=" + id);
        } else {
            found = bookService.update(found, newValues);
            return ResponseEntity.ok(found);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        Book found = bookService.findByIsbn(id);

        if (found == null) {
            throw new Exception("Role not found with id=" + id);
        } else {
            bookService.delete(found);
            return ResponseEntity.ok().body("Eliminado");
        }
    }
}
