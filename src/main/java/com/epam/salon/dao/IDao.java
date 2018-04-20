package com.epam.salon.dao;

import java.util.List;

public interface IDao <T> {
    List<T> getAll();
    T getById(long id);
    void deleteById(long id);
    void insert(T item);
    void update(long id, T item);
}
