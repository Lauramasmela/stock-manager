package com.lsof.stockmanager.implementations;

import com.lsof.stockmanager.repository.UserRepository;
import com.lsof.stockmanager.dto.UserDto;
import com.lsof.stockmanager.model.User;
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

    private final UserRepository repository;
    private final ObjectsValidator<UserDto> objectsValidator;


    @Override
    public Integer save(UserDto dto) {
        objectsValidator.validate(dto);
        User user = UserDto.toEntity(dto);
        User savedUser = repository.save(user);
        return savedUser.getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(user -> UserDto.fromEntity(user))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(user -> UserDto.fromEntity(user))
                .orElseThrow(()-> new EntityNotFoundException("No user was found with provided id"));
    }

    @Override
    public void delete(Integer id) {


    }
}
