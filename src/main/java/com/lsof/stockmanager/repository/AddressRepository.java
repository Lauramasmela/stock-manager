package com.lsof.stockmanager.repository;

import com.lsof.stockmanager.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
