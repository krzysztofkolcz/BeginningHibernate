package com.kkolcz.dao;
 
import java.util.List;
import com.kkolcz.model.Product;
 
public interface ProductDao extends Dao<Product>{
    public List<Product> findBySkuExceptId(String sku,int id);
    public Product findBySku(String sku);
}
