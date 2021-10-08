package com.olmedo.bookborrowing.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "BORROWING")
public class Borrowing {@Id
    @Column(name="ORDER_ID", updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "OrderId is required")
    private String orderId;

    @Column(name="BORROW_DATE", updatable=true)
    private LocalDate borrowDate = LocalDate.now();

    @Column(name="DUE_DATE", updatable=true)
    private LocalDate dueDate;

    @Column(name="RENEWAL_FOIS", updatable=true)
    private int renewalFois = 0;

    @Column(name="PENALIZATION", updatable=true)
    private Double penalization = 0.0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ISBN", nullable = false)
    @NotNull(message = "Book is required")
    private Book bookObj;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull(message = "User is required")
    private User userObj;

}
