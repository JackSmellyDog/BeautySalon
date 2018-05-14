package com.kpi.salon.dao;

import java.util.List;

public interface IDao <T> {
    List<T> findAll();
    boolean deleteById(Long id);
    boolean findById(Long id);
    void insert(T item);
}
