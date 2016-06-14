package com.kkolcz.fixture;

import java.util.HashSet;
import java.math.BigDecimal;

import com.kkolcz.command.ProductCommand;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.State;
import com.kkolcz.model.Product;

public class Create{

    public static ProductCommand createProductCommand(int id, String name, String price, String sku, boolean active, String state ){
        ProductCommand product = new ProductCommand();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setSku(sku);
        product.setState(state);
        product.setActive(active);

        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1);
        productCategory.setName("4 jelenie");
        productCategories.add(productCategory);
        product.setProductCategories(productCategories);

        return product;
    }

    public static ProductCommand createFilledProductCommand(){
        ProductCommand product = new ProductCommand();
        product.setId(1);
        product.setName("name");
        product.setPrice("10.00");
        product.setSku("123-123-123");
        product.setState(State.ACTIVE.getState());
        product.setActive(true);

        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1);
        productCategory.setName("4 jelenie");
        productCategories.add(productCategory);
        product.setProductCategories(productCategories);

        return product;
    }





    public ProductCategory getProductCategory1(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1); 
        productCategory.setName("4 jelenie"); 
        return productCategory;
    }

    public ProductCategory getProductCategory2(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(2); 
        productCategory.setName("Samos"); 
        return productCategory;
    }

    public Product getProduct1(){
        Product product = new Product();
        product.setId(1);
        product.setName("Schabowy zestaw");
        product.setPrice(new BigDecimal("17.50"));
        product.setActive(true);
        product.setState("Active");
        product.setSku("000-000-001");
        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        productCategories.add(getProductCategory2()); 
        product.setProductCategories(productCategories);
        return product;
    }


    public Product getProduct2(){
        Product product = new Product();
        product.setId(2);
        product.setName("Polędwiczki grillowane");
        product.setPrice(new BigDecimal("16.00"));
        product.setActive(true);
        product.setState("Active");
        product.setSku("000-000-002");
        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        productCategories.add(getProductCategory2()); 
        product.setProductCategories(productCategories);
        return product;
    }
}
