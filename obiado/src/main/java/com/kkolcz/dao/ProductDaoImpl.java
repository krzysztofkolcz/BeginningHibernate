package com.kkolcz.dao;

import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.kkolcz.model.Product;
 
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

    public String getNaturalKeyName(){
      return "sku";
    }

    public List<Product> findBySku(String sku) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("sku", sku));
        List<Product> products = (List<Product>)crit.list();
        /* Product product = (Product) crit.uniqueResult(); */
        return products;
    }

    public List<Product> findBySkuExceptId(String sku,int id){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("sku", sku));
        crit.add(Restrictions.ne("id", id));
        List<Product> products = (List<Product>)crit.list();
        return products;
    }

}
