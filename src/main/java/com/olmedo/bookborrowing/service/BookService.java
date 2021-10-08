package com.olmedo.bookborrowing.service;

import com.olmedo.bookborrowing.entity.Book;
import com.olmedo.bookborrowing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){return bookRepository.findAll();}

    public Book findByIsbn(String ISBN){return bookRepository.findByBookISBN(ISBN);}


    public Book create(Book newBook){return bookRepository.save(newBook);}
}
