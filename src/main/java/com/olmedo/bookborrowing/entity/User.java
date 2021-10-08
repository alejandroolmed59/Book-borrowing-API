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
@Table(name = "USER")
public class User {
    @Id
    @Column(name="USER_ID", updatable=false)
    @NotNull(message = "CARNET is required")
    private String userId;

    @Column(name="NAME", updatable=true)
    @NotNull(message = "Name is required")
    private String name;

    @Column(name="EMAIL", updatable=true)
    @NotNull(message = "Email is required")
    private String email;

    @Column(name="PHONE_NUMBER", updatable=true)
    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @Column(name="BORROWED_BOOKS", updatable=true, columnDefinition = "Default is 0")
    private int borrowedBooks=0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    @NotNull(message = "BedType is required")
    private Role roleObj;
}
