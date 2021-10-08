package com.olmedo.bookborrowing.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name="BOOK_ISBN", updatable=false)
    @NotNull(message = "ISBN is required")
    private String bookISBN;

    @Column(name="TITLE", updatable=true)
    @NotNull(message = "Title is required")
    private String title;

    @Column(name="YEAR", updatable=true)
    @NotNull(message = "Year is required")
    private String year;

    @Column(name="AUTHOR", updatable=true)
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GENRE_ID", nullable = false)
    @NotNull(message = "Genre is required")
    private Genre genreObj;
}
