package com.lsof.stockmanager.controller;

import com.lsof.stockmanager.dto.TransactionDto;
import com.lsof.stockmanager.service.TransactionServiceContract;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceContract transactionService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDto>>findAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("transaction-id") Integer transactionId){
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<TransactionDto>>findAllByUserId(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(transactionService.findAllByUserId(userId));
    }

    @DeleteMapping("/{transaction-id}")
    public ResponseEntity<Void> delete(@PathVariable("transaction-id") Integer transactionId){
        transactionService.delete(transactionId);
        return ResponseEntity.accepted().build();
    }

}
