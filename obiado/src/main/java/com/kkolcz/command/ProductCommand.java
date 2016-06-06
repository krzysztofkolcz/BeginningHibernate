package com.kkolcz.command;

import java.io.Serializable;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.kkolcz.annotation.ValidPrice;/* TODO */
import com.kkolcz.annotation.ValidSku;/* TODO */
import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.Product;
import com.kkolcz.validator.*;
import java.math.BigDecimal;

public class ProductCommand extends AbstractCommand implements Serializable{

    private static final long serialVersionUID = 3L;

    /* private int id; */

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    @ValidPrice
    private String price;

    @NotNull
    private boolean active;

    @NotNull
    @NotEmpty
    @ValidSku /* powiedzmy, że format xxx-xxx-xxx ,gdzie x jest cyfrą lub literą */
    private String sku;

    @NotNull
    private Set<ProductCategory> productCategories; 

    public ProductCommand(){
    }

    public ProductCommand(Product product){
      this.id = product.getId();
      this.name = product.getName();
      this.price = product.getPrice().toString();
      this.sku = product.getSku();
      this.active = product.isActive();
      this.state = product.getState();
      this.productCategories = product.getProductCategories(); 
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean getActive() {
        return active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }
 
    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    /* public int getId() { */
    /*     return id; */
    /* } */
    /*  */
    /* public void setId(int id) { */
    /*     this.id = id; */
    /* } */

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
