package com.kkolcz.dao;

import java.util.List;

public interface Dao<T> {
    T findById(Integer id);
    List<T> findAll();
    void save(T p);
}
