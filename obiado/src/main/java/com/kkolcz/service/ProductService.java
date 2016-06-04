package com.kkolcz.service;

import com.kkolcz.model.Product;
import com.kkolcz.command.ProductCommand;
import java.util.List;

public interface ProductService{
    List<Product> findAll();
    Product findById(int id);
    boolean skuExists(String sku);
    boolean skuExistsExceptId(String sku,int productId);
    void update(ProductCommand productCommand);
    void add(ProductCommand productCommand,Product product);
}
