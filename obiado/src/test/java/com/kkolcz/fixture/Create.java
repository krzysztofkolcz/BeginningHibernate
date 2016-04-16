package com.kkolcz.fixture;

import java.util.HashSet;

import com.kkolcz.command.ProductCommand;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.State;

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
}
