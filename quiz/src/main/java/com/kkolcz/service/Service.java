package com.kkolcz.service;

import java.util.List;

public interface Service<T>{
    T findById(int id);
    List<T> findAll();
    public void saveOrUpdate(T object);
    public void delete(int id);
    public void delete(T object);
}
