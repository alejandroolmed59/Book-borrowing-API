package com.olmedo.bookborrowing.service;

import com.olmedo.bookborrowing.entity.Borrowing;
import com.olmedo.bookborrowing.entity.Genre;
import com.olmedo.bookborrowing.repository.BorrowingRepository;
import com.olmedo.bookborrowing.utils.Constants;
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

    public Borrowing getBorrow(String UserId, String BookISBN){return borrowingRepository.findByUserObjUserIdAndBookObjBookISBNAndDeliveredFalse(UserId, BookISBN);}

    public Borrowing penalizeAndDeliver(Borrowing returningBook, double fee){
        returningBook.setPenalization(fee);
        return delivered(returningBook);
    }

    public Borrowing delivered(Borrowing returnedBook) {
        returnedBook.setDelivered(true);
        returnedBook.setRealReturnDate(LocalDate.now());
        return borrowingRepository.save(returnedBook);
    }

    public Borrowing renew(Borrowing renewedBook) {
        renewedBook.setBorrowDate(LocalDate.now());
        renewedBook.setDueDate(LocalDate.now().plusDays(Constants.MAX_BORROW_DAYS));
        renewedBook.setRenewalFois(renewedBook.getRenewalFois()+1);
        return borrowingRepository.save(renewedBook);
    }

    public Borrowing findById(String id) {
        return borrowingRepository.findByOrderId(id);
    }

    public Borrowing update(Borrowing found, Borrowing newValues) {
        found.setBorrowDate(newValues.getBorrowDate());
        found.setDueDate(newValues.getDueDate());
        found.setRealReturnDate(newValues.getRealReturnDate());
        found.setRenewalFois(newValues.getRenewalFois());
        found.setPenalization(newValues.getPenalization());
        found.setDelivered(newValues.getDelivered());
        return borrowingRepository.save(found);
    }
    public void delete(Borrowing found) {
        borrowingRepository.delete(found);
    }
}
