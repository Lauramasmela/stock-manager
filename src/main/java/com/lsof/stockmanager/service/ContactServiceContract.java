package com.lsof.stockmanager.service;

import com.lsof.stockmanager.dto.ContactDto;

import java.util.List;

public interface ContactServiceContract extends GenericDbService<ContactDto>{
    List<ContactDto> findAllByUserId(Integer userId);
}
