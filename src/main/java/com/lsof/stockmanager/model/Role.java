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
public class Role extends AbstractEntity {

    private String role;

    @OneToOne
    @JoinColumn(name = "id_user")
    User user;

}
