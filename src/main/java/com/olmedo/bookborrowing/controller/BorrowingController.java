package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.Borrowing;
import com.olmedo.bookborrowing.service.BorrowingService;
import com.olmedo.bookborrowing.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowingController {
    @Autowired
    BorrowingService borrowingService;

    @GetMapping("/")
    public List<Borrowing> getAllBorrows() {
        List<Borrowing> borrows = borrowingService.findAll();
        return borrows;
    }

    @PostMapping("/")
    public Borrowing create(@RequestBody Borrowing borrow) throws Exception {
        Borrowing createdBorrow= borrowingService.create(borrow);

        if (createdBorrow==null) {
            throw new Exception("Genre was not created");
        } else {
            return borrow;
        }
    }
    @PostMapping("/testis")
    public ResponseEntity<Borrowing> testis(@RequestBody Borrowing borrowing) {
        System.out.println(borrowing);
        borrowing = formatBorrowEntity(borrowing);

        Borrowing createdBorrowing = borrowingService.create(borrowing);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("estado", "nice1");
        return new ResponseEntity<Borrowing>(createdBorrowing, responseHeaders, HttpStatus.CREATED);
    }

    public Borrowing formatBorrowEntity(Borrowing borrowing){
        if(borrowing.getDueDate()==null){
            borrowing.setDueDate(borrowing.getBorrowDate().plusDays(Constants.MAX_BORROW_DAYS));
        }

        return borrowing;
    }
}
