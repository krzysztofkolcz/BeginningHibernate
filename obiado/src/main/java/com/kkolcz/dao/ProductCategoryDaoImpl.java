package com.kkolcz.dao;

import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
 
import com.kkolcz.model.ProductCategory;
 
@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends AbstractDao<Integer, ProductCategory> implements ProductCategoryDao {

    public ProductCategory findById(int id) {
        ProductCategory productCategory = getByKey(id);
        return productCategory;
    }
 
   @SuppressWarnings("unchecked")
    public List<ProductCategory> findAllProductCategories() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ProductCategory> productCategories = (List<ProductCategory>) criteria.list();
        return productCategories;
    }

    public ProductCategory findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        ProductCategory productCategory = (ProductCategory) crit.uniqueResult();
        return productCategory;
    }

    public List<ProductCategory> findByNameExceptId(String name,int id){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        crit.add(Restrictions.ne("id", id));
        List<ProductCategory> productCategories = (List<ProductCategory>)crit.list();
        return productCategories;
    }

    public void persistProductCategory(ProductCategory productCategory) {
        persist(productCategory);
    }

    public void saveProductCategory(ProductCategory productCategory) {
        save(productCategory);
    }
 
    public void removeAll(){
      //TODO
    }
}
