package com.kkolcz.dao;
import java.util.List;
 
public interface Dao<P>{
    public P findById(Integer id);
    public List<P> findAll();
    public List<P> findAll(String orderBy);
    public void persist(P p);
    public void save(P p);
    public void removeAll();

    /* TODO - zastąpić przez findByNaturalKey */
    /* public P findByName(String name); */
    /* public List<P> findByNameExceptId(String name,int id); */

    public P findByNaturalKey(String key);
    public List<P> findByNaturalKeyExceptId(String key,int id);
}
