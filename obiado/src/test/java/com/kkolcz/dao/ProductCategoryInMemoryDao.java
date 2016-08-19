package com.kkolcz.dao;

import com.kkolcz.model.ProductCategory;
import com.kkolcz.dao.ProductCategoryDao;
import java.util.List;
import java.util.ArrayList;
 
public class ProductCategoryInMemoryDao extends AbstractInMemoryDao<ProductCategory> implements ProductCategoryDao {

    public String getNaturalKeyName(){
      return "name";
    }

    public ProductCategory findById(Integer id){
      return this.getByKey(id);
    }
     

    public ProductCategory findByName(String name){
        for(ProductCategory pc : dao){
           if(pc.getName() == name){
             return pc;
           } 
        }
        return null;
    }

    public List<ProductCategory> findAll(){
        return this.dao;
    }

    /* TODO - implement comparicon */
    public List<ProductCategory> findAll(String order){
        return this.dao;
    }

    public void persistProductCategory(ProductCategory pc){
      this.persist(pc);
    }


    public void removeAll(){
      dao.removeAll(dao);
    }

    public List<ProductCategory> findByNameExceptId(String name,int id){
        List<ProductCategory> found = new ArrayList<ProductCategory>();
        for(ProductCategory pc : dao){
           if(pc.getName()==name && pc.getId()!=id){
             found.add(pc);
           } 
        }
        return found;
    }
}
