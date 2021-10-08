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
@Table(name = "GENRE")
public class Genre {
    @Id
    @Column(name="GENRE_ID", updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    @Column(name="GENRE_TYPE", updatable=true)
    @NotNull(message = "Genre type is required")
    private String genreType;
}
