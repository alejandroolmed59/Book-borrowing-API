package com.olmedo.bookborrowing.service;

import com.olmedo.bookborrowing.entity.Borrowing;
import com.olmedo.bookborrowing.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {
    @Autowired
    BorrowingRepository borrowingRepository;


    public List<Borrowing> findAll(){return borrowingRepository.findAll();}

    public Borrowing create(Borrowing newBorrow){return borrowingRepository.save(newBorrow);}

    public Borrowing getBorrow(String UserId, String BookISBN){return borrowingRepository.findByUserObjUserIdAndBookObjBookISBN(UserId, BookISBN);}

    public Borrowing penalizeAndDeliver(Borrowing returningBook, double fee){
        returningBook.setPenalization(fee);
        return delivered(returningBook);
    }

    public Borrowing delivered(Borrowing returnedBook) {
        returnedBook.setDelivered(true);
        returnedBook.setRealReturnDate(LocalDate.now());
        return borrowingRepository.save(returnedBook);
    }
}
