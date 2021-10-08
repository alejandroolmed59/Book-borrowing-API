package com.olmedo.bookborrowing.service;

import com.olmedo.bookborrowing.entity.Borrowing;
import com.olmedo.bookborrowing.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingService {
    @Autowired
    BorrowingRepository borrowingRepository;

    public List<Borrowing> findAll(){return borrowingRepository.findAll();}

    public Borrowing create(Borrowing newBorrow){return borrowingRepository.save(newBorrow);}
}
