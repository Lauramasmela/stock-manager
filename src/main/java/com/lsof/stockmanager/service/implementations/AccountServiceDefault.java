package com.lsof.stockmanager.service.implementations;

import com.lsof.stockmanager.dto.AccountDto;
import com.lsof.stockmanager.exception.OperationNonPermittedException;
import com.lsof.stockmanager.model.Account;
import com.lsof.stockmanager.repository.AccountRepository;
import com.lsof.stockmanager.service.AccountServiceContract;
import com.lsof.stockmanager.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceDefault implements AccountServiceContract {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> objectsValidator;

    @Override
    public Integer save(AccountDto dto) {

        // block account update -> iban cannot be changed
    /* if (dto.getId() != null) {
      throw new OperationNonPermittedException(
          "Account cannot be updated",
          "save account",
          "Account",
          "Update not permitted"
      );
    }*/
        objectsValidator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()) {
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // generate random IBAN when creating new account else do not update the IBAN
        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(account -> AccountDto.fromEntity(account))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(account -> AccountDto.fromEntity(account))
                .orElseThrow(()-> new EntityNotFoundException("No account was found with the provided id"));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        String iban = Iban.random(CountryCode.FR).toFormattedString();
        boolean ibanExists = repository.findByIban(iban).isPresent();
        if(ibanExists){
            generateRandomIban();
        }
        return iban;
    }
}
