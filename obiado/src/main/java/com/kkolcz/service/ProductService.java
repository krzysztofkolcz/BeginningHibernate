package com.kkolcz.service;

import com.kkolcz.model.Product;
import com.kkolcz.command.ProductCommand;
import java.util.List;

public interface ProductService{
    List<Product> findAllProducts();
    Product findById(int id);
    boolean checkSkuUnique(String sku);
    boolean checkSkuUniqueExceptId(String sku,int productId);
    Product updateProduct(ProductCommand productCommand);
    Product addProduct(ProductCommand productCommand);
}
