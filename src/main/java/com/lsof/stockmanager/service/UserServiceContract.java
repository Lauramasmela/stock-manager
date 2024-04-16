package com.lsof.stockmanager.service;

import com.lsof.stockmanager.dto.UserDto;


public interface UserServiceContract extends GenericDbService<UserDto> {

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);
}
