package com.olmedo.bookborrowing.repository;

import com.olmedo.bookborrowing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookISBN(String ISBN);
}
