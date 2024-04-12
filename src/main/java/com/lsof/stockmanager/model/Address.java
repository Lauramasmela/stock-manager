package com.lsof.stockmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends GenericEntity {

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String county;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
