package com.lsof.stockmanager.service;


import java.util.List;

public interface DbService<T> {

    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}

