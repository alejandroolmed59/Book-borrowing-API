package com.olmedo.bookborrowing.controller;

import com.olmedo.bookborrowing.entity.Book;
import com.olmedo.bookborrowing.entity.Borrowing;
import com.olmedo.bookborrowing.entity.User;
import com.olmedo.bookborrowing.exception.BookAlreadyReservedException;
import com.olmedo.bookborrowing.exception.MaxBooksPerUserException;
import com.olmedo.bookborrowing.pojo.ReturnBook;
import com.olmedo.bookborrowing.repository.UserRepository;
import com.olmedo.bookborrowing.service.BookService;
import com.olmedo.bookborrowing.service.BorrowingService;
import com.olmedo.bookborrowing.service.UserService;
import com.olmedo.bookborrowing.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowingController {
    @Autowired
    BorrowingService borrowingService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public List<Borrowing> getAllBorrows() {
        List<Borrowing> borrows = borrowingService.findAll();
        return borrows;
    }


    @PostMapping("/borrow")
    public ResponseEntity<Borrowing> create(@RequestBody Borrowing borrowing) throws Exception {
        System.out.println(borrowing);
        borrowing = formatBorrowEntity(borrowing);

        Borrowing createdBorrowing = borrowingService.create(borrowing);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("estado", "created");
        return new ResponseEntity<Borrowing>(createdBorrowing, responseHeaders, HttpStatus.CREATED);
    }
    //TODO: RESTAR UN LIBRO CUANDO SE DEVUELVE AL USUARIO Y CAMBIAR DISPONIBILIDAD A TRUE EN EL LIBRO ---- DONE ✔️
    @PostMapping("/return")
    public ResponseEntity<Borrowing> returnBook(@RequestBody ReturnBook returnObj) throws Exception {
        Borrowing returnedBook = borrowingService.getBorrow(returnObj.getUserId(), returnObj.getBookISBN());
        if (returnedBook==null) {
            throw new Exception("Borrowing not found");
        }
        //RESTAR LIBRO AL USUARIO
        User currUser = returnedBook.getUserObj();
        currUser.setBorrowedBooks(currUser.getBorrowedBooks()-1);
        userRepository.save(currUser);
        returnedBook.setUserObj(currUser);
        //CAMBIAR DISPONIBILIDAD LIBRO
        Book currBook = returnedBook.getBookObj();
        currBook.setAvailable(true);
        bookService.create(currBook);
        returnedBook.setBookObj(currBook);
        //PENALIZATION AND RETURN
        int daysAfterBorrow = (int) ChronoUnit.DAYS.between(returnedBook.getBorrowDate(), LocalDate.now());
        if(daysAfterBorrow>Constants.MAX_BORROW_DAYS){
            returnedBook = borrowingService.penalizeAndDeliver(returnedBook, Constants.PENALIZATION_FEE_PER_DAY_LATE*(daysAfterBorrow-Constants.MAX_BORROW_DAYS));
        }else{
            returnedBook = borrowingService.delivered(returnedBook);
        }
        return ResponseEntity.ok(returnedBook);
    }

    @PostMapping("/renew")
    public ResponseEntity renewBook(@RequestBody ReturnBook returnObj) throws Exception{
        Borrowing renewedBook = borrowingService.getBorrow(returnObj.getUserId(), returnObj.getBookISBN());
        if (renewedBook==null) {
            throw new Exception("Borrowing not found");
        }
        User currUser = renewedBook.getUserObj();
        Book currBook = renewedBook.getBookObj();
        //NOT POSSIBLE
        int daysAfterBorrow = (int) ChronoUnit.DAYS.between(renewedBook.getBorrowDate(), LocalDate.now());
        if(renewedBook.getRenewalFois()>=Constants.MAX_RENEWAL_TIMES || daysAfterBorrow>Constants.MAX_BORROW_DAYS) {
            //RESTAR LIBRO AL USUARIO

            currUser.setBorrowedBooks(currUser.getBorrowedBooks()-1);
            userRepository.save(currUser);
            renewedBook.setUserObj(currUser);
            //CAMBIAR DISPONIBILIDAD LIBRO

            currBook.setAvailable(true);
            bookService.create(currBook);
            renewedBook.setBookObj(currBook);
            if(daysAfterBorrow>Constants.MAX_BORROW_DAYS){
                renewedBook = borrowingService.penalizeAndDeliver(renewedBook, Constants.PENALIZATION_FEE_PER_DAY_LATE*(daysAfterBorrow-Constants.MAX_BORROW_DAYS));
            }
            if(renewedBook.getRenewalFois()>=Constants.MAX_RENEWAL_TIMES){
                renewedBook = borrowingService.delivered(renewedBook);
            }
            return ResponseEntity.badRequest().body("Couldn't renew, book was returned");
        }else{
            renewedBook = borrowingService.renew(renewedBook);
            return ResponseEntity.ok().body(renewedBook);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Borrowing newValues, @PathVariable String id) throws Exception {
        Borrowing found = borrowingService.findById(id);

        if (found == null) {
            throw new Exception("Book were not found with id=" + id);
        } else {
            found = borrowingService.update(found, newValues);
            return ResponseEntity.ok(found);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        Borrowing found = borrowingService.findById(id);

        if (found == null) {
            throw new Exception("Role were not found with id=" + id);
        } else {
            borrowingService.delete(found);
            return ResponseEntity.ok().body("Eliminado");
        }
    }

    public Borrowing formatBorrowEntity(Borrowing borrowing) throws Exception{
        User user = userRepository.findByUserId(borrowing.getUserObj().getUserId());
        Book book = bookService.findByIsbn(borrowing.getBookObj().getBookISBN());
        if(user.getBorrowedBooks()>=3){
            throw new MaxBooksPerUserException("User can no borrow more than "+Constants.MAX_BOOKS_PER_USER+" books at the same time");
        }
        if(book.getAvailable()==false){
            throw new BookAlreadyReservedException("Book is already taken!");
        }
        if(borrowing.getDueDate()==null){
            borrowing.setDueDate(borrowing.getBorrowDate().plusDays(Constants.MAX_BORROW_DAYS));
        }

        user.setBorrowedBooks(user.getBorrowedBooks()+1);
        userRepository.save(user);

        book.setAvailable(false);
        bookService.create(book);
        return borrowing;
    }

}
