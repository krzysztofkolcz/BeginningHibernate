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

public class ProductCategoryCommand implements Serializable{

    private static final long serialVersionUID = 4L;

    private int id;

    @NotNull
    @NotEmpty
    private String name;

    public ProductCategoryCommand(){
    }

    public ProductCategoryCommand(ProductCategory productCategory){
      this.id = productCategory.getId();
      this.name = productCategory.getName();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
