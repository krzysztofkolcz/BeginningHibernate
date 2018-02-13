package com.kkolcz.dao;

import java.util.List;

public interface Dao<T> {
    T findById(Integer id);
    List<T> findAll();
    void save(T p);
    void saveOrUpdate(T p);
    void delete(int id);
    void delete(T object);
    void merge(T object);
    void update(T object);
}
