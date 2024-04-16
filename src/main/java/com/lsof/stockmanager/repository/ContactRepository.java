package com.lsof.stockmanager.repository;

import com.lsof.stockmanager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
