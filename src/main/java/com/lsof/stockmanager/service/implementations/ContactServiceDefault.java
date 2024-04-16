package com.lsof.stockmanager.service.implementations;

import com.lsof.stockmanager.dto.ContactDto;
import com.lsof.stockmanager.model.Contact;
import com.lsof.stockmanager.repository.ContactRepository;
import com.lsof.stockmanager.service.ContactServiceContract;
import com.lsof.stockmanager.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceDefault implements ContactServiceContract {

    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> objectsValidator;

    @Override
    public Integer save(ContactDto dto) {
        objectsValidator.validate(dto);
        Contact contact =  ContactDto.toEntity(dto);
        Contact contactSaved = repository.save(contact);
        return contactSaved.getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return null;
    }

    @Override
    public ContactDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(contact -> ContactDto.fromEntity(contact))
                .collect(Collectors.toList());
    }
}
