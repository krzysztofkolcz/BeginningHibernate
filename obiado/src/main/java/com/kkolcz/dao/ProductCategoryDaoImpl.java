package com.kkolcz.dao;

import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.kkolcz.model.ProductCategory;
 
@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends AbstractDao<Integer, ProductCategory> implements ProductCategoryDao {

    @SuppressWarnings("unchecked")
    public List<ProductCategory> findAll() {
      return super.findAll("name");
    }
 
}
