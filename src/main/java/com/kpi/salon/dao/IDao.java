package com.kpi.salon.dao;

import java.util.List;

public interface IDao <T> {
    List<T> findAll();
    boolean deleteById(Long id);
    T findById(Long id);
    boolean insert(T item);
    boolean update(T item);
}
