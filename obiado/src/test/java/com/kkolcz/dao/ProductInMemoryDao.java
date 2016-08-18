package com.kkolcz.dao;

import com.kkolcz.model.Product;
import com.kkolcz.dao.ProductDao;
import java.util.List;
import java.util.ArrayList;
 
public class ProductInMemoryDao extends AbstractInMemoryDao<Product> implements ProductDao {
    
    public String getNaturalKeyName(){
      return "sku";
    }

    public Product findById(Integer id){
      return this.getByKey(id);
    }
     

    public Product findByName(String name){
        for(Product pc : dao){
           if(pc.getName() == name){
             return pc;
           } 
        }
        return null;
    }

    public List<Product> findAll(){
        return this.dao;
    }

    /* Na razie nie działa jak należy */
    public List<Product> findAll(String orderBy){
      /* Collections.sort(dao, new Comparator<Product>() { */
      /*       @Override */
      /*       public int compare(Product p2, Product p1) */
      /*       { */
      /*           return  p1.getName().compareTo(p2.getName()); */
      /*       } */
      /*   }); */
        return this.dao;
    }

    public void persist(Product pc){
      this.persist(pc);
    }



    public void removeAll(){
      dao.removeAll(dao);
    }

    public List<Product> findByNameExceptId(String name,int id){
        List<Product> found = new ArrayList<Product>();
        for(Product pc : dao){
           if(pc.getName()==name && pc.getId()!=id){
             found.add(pc);
           } 
        }
        return found;
    }

    public List<Product> findBySkuExceptId(String sku,int id){
        List<Product> found = new ArrayList<Product>();
        for(Product pc : dao){
           if(pc.getSku()==sku && pc.getId()!=id){
             found.add(pc);
           } 
        }
        return found;

    }

    public List<Product> findBySku(String sku){
        List<Product> found = new ArrayList<Product>();
        for(Product pc : dao){
           if(pc.getSku() == sku){
             found.add(pc);
           } 
        }
        return found;
    }
}
