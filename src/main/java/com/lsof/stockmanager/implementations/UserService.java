package com.lsof.stockmanager.implementations;

import com.lsof.stockmanager.Repository.UserRepository;
import com.lsof.stockmanager.dto.UserDto;
import com.lsof.stockmanager.model.User;
import com.lsof.stockmanager.service.UserServiceContract;
import com.lsof.stockmanager.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceContract {

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
        return null;
    }

    @Override
    public UserDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
