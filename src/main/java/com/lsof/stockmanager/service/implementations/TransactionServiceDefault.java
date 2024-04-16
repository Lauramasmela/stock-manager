package com.lsof.stockmanager.service.implementations;

import com.lsof.stockmanager.dto.TransactionDto;
import com.lsof.stockmanager.model.Transaction;
import com.lsof.stockmanager.model.TransactionType;
import com.lsof.stockmanager.repository.TransactionRepository;
import com.lsof.stockmanager.service.TransactionServiceContract;
import com.lsof.stockmanager.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceDefault implements TransactionServiceContract {

    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDto> objectsValidator;


    @Override
    public Integer save(TransactionDto dto) {
        objectsValidator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal  transactionMultiplier = BigDecimal.valueOf(transactionType(transaction.getType()));
        BigDecimal amount = transaction.getAmount()
                .multiply(transactionMultiplier);
        transaction.setAmount(amount);
        Transaction transactionSaved = repository.save(transaction);
        return transactionSaved.getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(transaction -> TransactionDto.fromEntity(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id).map(transaction -> TransactionDto.fromEntity(transaction))
                .orElseThrow(()-> new EntityNotFoundException("No transaction was found with provided id"));
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id);
    }

    private int transactionType(TransactionType type){
        return TransactionType.TRANSFERT == type ? -1 : 1;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(transaction -> TransactionDto.fromEntity(transaction))
                .collect(Collectors.toList());
    }
}
