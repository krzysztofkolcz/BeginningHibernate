package com.kkolcz.service;

import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.Product;
import com.kkolcz.command.ProductCategoryCommand;
import java.util.List;

public interface ProductCategoryService{
    List<ProductCategory> findAllCategories();
    ProductCategory findById(int id);
    ProductCategory updateProduct(ProductCategoryCommand productCategoryCommand);
    ProductCategory addProductCategory(ProductCategoryCommand productCategoryCommand);

    List<ProductCategory> findAllProductCategories();
    boolean checkNameUnique(String name);
    boolean checkNameUniqueExceptId(String name,int id);

    void updateProductCategory(ProductCategoryCommand productCategoryCommand);

}
