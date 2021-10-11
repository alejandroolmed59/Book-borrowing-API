package com.olmedo.bookborrowing.repository;

import com.olmedo.bookborrowing.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Borrowing findByUserObjUserIdAndBookObjBookISBNAndDeliveredFalse(String userId, String BookISBN);

    Borrowing findByOrderId(String id);
}
