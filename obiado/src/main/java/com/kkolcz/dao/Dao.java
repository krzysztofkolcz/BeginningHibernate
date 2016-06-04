package com.kkolcz.dao;
import java.util.List;
 
public interface Dao<P>{
    P findById(int id);
    List<P> findAll();
    List<P> findAll(String orderBy);
    void persist(P p);
    void save(P p);
    void removeAll();
    public P findByName(String name);
    public List<P> findByNameExceptId(String name,int id);
}
