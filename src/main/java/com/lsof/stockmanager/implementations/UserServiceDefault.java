package com.lsof.stockmanager.implementations;

import com.lsof.stockmanager.dto.AccountDto;
import com.lsof.stockmanager.model.Account;
import com.lsof.stockmanager.repository.AccountRepository;
import com.lsof.stockmanager.repository.UserRepository;
import com.lsof.stockmanager.dto.UserDto;
import com.lsof.stockmanager.model.User;
import com.lsof.stockmanager.service.AccountServiceContract;
import com.lsof.stockmanager.service.UserServiceContract;
import com.lsof.stockmanager.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceDefault implements UserServiceContract {

    private final UserRepository userRepository;
    private final AccountServiceContract accountServiceContract;
    private final ObjectsValidator<UserDto> objectsValidator;


    @Override
    public Integer save(UserDto dto) {
        objectsValidator.validate(dto);
        User user = UserDto.toEntity(dto);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserDto.fromEntity(user))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(user -> UserDto.fromEntity(user))
                .orElseThrow(()-> new EntityNotFoundException("No user was found with provided id"));
    }

    @Override
    public void delete(Integer id) {


    }

    @Override
    public Integer validateAccount(Integer id) {
        User user  = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user was found for user account validation "));
        user.setActive(true);

        // create a bank account
        AccountDto accountDto = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountServiceContract.save(accountDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateaccount(Integer id) {
        User user  = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No user was found for user account validation "));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
