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

    public Borrowing getBorrow(String UserId, String BookISBN){return borrowingRepository.findByUserObjUserIdAndBookObjBookISBN(UserId, BookISBN);}

    public Borrowing penalize(Borrowing returningBook, double fee){
        returningBook.setPenalization(fee);
        return borrowingRepository.save(returningBook);
    }
}
