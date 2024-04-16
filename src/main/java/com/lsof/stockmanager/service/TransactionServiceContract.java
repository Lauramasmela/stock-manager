package com.lsof.stockmanager.service;

import com.lsof.stockmanager.dto.TransactionDto;
import com.lsof.stockmanager.model.Transaction;

import java.util.List;

public interface TransactionServiceContract extends GenericDbService<TransactionDto>{
    List<TransactionDto> findAllByUserId(Integer userId);
}
