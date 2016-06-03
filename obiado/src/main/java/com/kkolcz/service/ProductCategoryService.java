package com.kkolcz.service;

import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.Product;
import com.kkolcz.command.ProductCategoryCommand;
import java.util.List;

public interface ProductCategoryService{
    ProductCategory findById(int id);
    ProductCategory findByName(String name);
    List<ProductCategory> findAllProductCategories();
    boolean nameExist(String name);
    boolean nameExistExceptId(String name,int id);
    void addProductCategory(ProductCategoryCommand productCategoryCommand);
    void updateProductCategory(ProductCategoryCommand productCategoryCommand);
}
