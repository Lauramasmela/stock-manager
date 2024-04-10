package com.lsof.stockmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class Contact extends AbstractEntity {

    private String firstname;

    private String lastname;

    private String email;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
