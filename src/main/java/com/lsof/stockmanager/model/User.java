package com.lsof.stockmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User extends GenericEntity {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private boolean active;


    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Account account;

    @OneToOne
    private Address address;

    @OneToOne
    private Role role;

}