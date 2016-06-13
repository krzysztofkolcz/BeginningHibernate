package com.kkolcz.dao;

import java.util.ArrayList;
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
      /* if(entity.getId() == null){ */
      /*   int size = dao.size(); */
      /*   int id = size++; */
      /*   entity.setId(id); */
      /* } */
      System.out.println("========================");
      System.out.println(entity.getId());
      System.out.println("========================");
      dao.add(entity);
    }

    public void save(T entity) {
      dao.add(entity);
    }
 
    public void delete(T entity) {
      dao.remove(dao.indexOf(entity));
    }
     
    protected Criteria createEntityCriteria(){
      return null;
    }

 
}
