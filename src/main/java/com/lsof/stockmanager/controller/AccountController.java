package com.lsof.stockmanager.controller;

import com.lsof.stockmanager.dto.AccountDto;
import com.lsof.stockmanager.service.AccountServiceContract;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceContract accountService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(accountService.save(accountDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDto>>findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(@PathVariable("account-id") Integer accountId){
        return ResponseEntity.ok(accountService.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(@PathVariable("account-id") Integer accountId){
        accountService.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
