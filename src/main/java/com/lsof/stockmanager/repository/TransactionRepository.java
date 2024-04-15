package com.lsof.stockmanager.repository;

import com.lsof.stockmanager.dto.TransactionDto;
import com.lsof.stockmanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);
}
