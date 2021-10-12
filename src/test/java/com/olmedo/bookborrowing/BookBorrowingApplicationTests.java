package com.olmedo.bookborrowing;

import com.olmedo.bookborrowing.controller.BorrowingController;
import com.olmedo.bookborrowing.entity.Book;
import com.olmedo.bookborrowing.entity.Borrowing;
import com.olmedo.bookborrowing.entity.User;
import com.olmedo.bookborrowing.pojo.ReturnBook;
import com.olmedo.bookborrowing.service.BookService;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class BookBorrowingApplicationTests {


    @Autowired
    private BorrowingController borrowingController;

   @Test
    void contextLoads() throws Exception{
        assertThat(borrowingController).isNotNull();
    }
    @Test
    void createBorrow() throws Exception {
        Borrowing borrowing = new Borrowing();
        Book book = new Book();
        User user = new User();

        book.setBookISBN("4862579499");
        user.setUserId("00041017");
        borrowing.setBookObj(book);
        borrowing.setUserObj(user);
        assertThat(borrowingController.create(borrowing));
    }


    @Test
    void retunrBookTest() throws Exception {
        String userId="00097017";
        String bookISBN="0192861891"; //ERR W/ 3124412342
        assertThat(borrowingController.returnBook(new ReturnBook(userId, bookISBN)));

    }

}
