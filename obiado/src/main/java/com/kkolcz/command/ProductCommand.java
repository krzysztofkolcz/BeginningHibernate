package com.kkolcz.command;

import java.io.Serializable;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.kkolcz.annotation.ValidPrice;/* TODO */
import com.kkolcz.model.ProductCategory;/* TODO */
import com.kkolcz.model.Product;/* TODO */
import com.kkolcz.validator.*;
import java.math.BigDecimal;

@PasswordMatches 
public class ProductCommand implements Serializable{

    private static final long serialVersionUID = 2L;

    private int id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @ValidPrice
    private BigDecimal price;

    @NotNull
    @NotEmpty
    private boolean active;

    @NotNull
    private Set<ProductCategory> productCategories; 

    public ProductCommand(){
    }

    public ProductCommand(Product product){
      this.id = product.getId();
      this.name = product.getName();
      this.price = product.getPrice();
      this.active= product.isActive();
      this.productCategories = product.getProductCategories(); 
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
