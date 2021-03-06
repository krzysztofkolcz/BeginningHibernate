package com.kkolcz.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import com.kkolcz.model.Model; 
 
public abstract class AbstractInMemoryDao<T extends Model> {
     
    protected ArrayList<T> dao = new ArrayList<T>();
     
    public AbstractInMemoryDao(){
    }
     
    public T getByKey(int key) {
        for(T d: dao){
           if(((T)d).getId()==key){
             return d;
           } 
        }
        return null;
    }
 
    public void persist(T entity) {
      dao.add(entity);
    }

    public void save(T entity) {
      dao.add(entity);
    }
 
    public void delete(T entity) {
      dao.remove(dao.indexOf(entity));
    }

    public void merge(T entity) {
    }
     
    protected Criteria createEntityCriteria(){
      return null;
    }

    public T findByNaturalKey(String naturalKeyValue){
        for(T t : dao){
           if(t.getNaturalKey()==naturalKeyValue){
             return t;
           } 
        }
        return null;
    }

    public T findByNaturalKeyExceptId(String naturalKeyValue,int id){
        /* List<T> found = new ArrayList<T>(); */
        for(T t : dao){
           if(t.getNaturalKey() == naturalKeyValue && t.getId()!=id){
             return t;
           } 
        }
        return null;
    }

}
