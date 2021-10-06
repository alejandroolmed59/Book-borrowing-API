package com.olmedo.bookborrowing.entity;
import java.io.Serializable;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ROLE")
public class Role implements Serializable{
    private static final long serialVersionUID = 5433713775530016813L;

    @Id
    @Column(name="ROLE_ID", updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name="ROLE_TYPE", updatable=true)
    @NotNull(message = "Role type is required")
    private String roleType;
}
