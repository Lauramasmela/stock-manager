package com.lsof.stockmanager.implementations;

import com.lsof.stockmanager.dto.AddressDto;
import com.lsof.stockmanager.model.Account;
import com.lsof.stockmanager.model.Address;
import com.lsof.stockmanager.repository.AddressRepository;
import com.lsof.stockmanager.service.AddressServiceContract;
import com.lsof.stockmanager.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceDefault implements AddressServiceContract {

    private final AddressRepository repository;
    private final ObjectsValidator<AddressDto> objectsValidator;

    @Override
    public Integer save(AddressDto dto) {
        objectsValidator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        Address addressSaved = repository.save(address);
        return addressSaved.getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll()
                .stream()
                .map(address -> AddressDto.fromEntity(address))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(address -> AddressDto.fromEntity(address))
                .orElseThrow(()-> new EntityNotFoundException("No address was found with provided id"))
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}
