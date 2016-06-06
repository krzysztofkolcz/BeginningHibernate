package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.ProductCategory;
 
public interface ProductCategoryDao {
    void persist(ProductCategory productCategory);
    void save(ProductCategory productCategory);
    ProductCategory findById(int id);
    List<ProductCategory> findAllProductCategories();
    void persistProductCategory(ProductCategory productCategory);
    void saveProductCategory(ProductCategory productCategory);
    void removeAll();
    public ProductCategory findByName(String name) ;
    public List<ProductCategory> findByNameExceptId(String name,int id);
}
