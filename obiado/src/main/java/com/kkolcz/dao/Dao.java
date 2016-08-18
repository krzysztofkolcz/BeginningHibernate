package com.kkolcz.dao;
import java.util.List;
 
public interface Dao<P>{
    public P findById(Integer id);
    public List<P> findAll();
    public List<P> findAll(String orderBy);
    public void persist(P p);
    public void save(P p);
    public void removeAll();

    public String getNaturalKeyName();
    public P findByNaturalKey(String keyValue);
    public P findByNaturalKeyExceptId(String keyValue,int id);
}
