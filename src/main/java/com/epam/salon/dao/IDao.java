package com.epam.salon.dao;

import java.util.List;

public interface IDao <T> {
    List<T> findAll();
    void insert(T item);
}
