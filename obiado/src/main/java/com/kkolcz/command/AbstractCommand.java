package com.kkolcz.command;

import java.io.Serializable;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.Product;
import com.kkolcz.validator.*;
import java.math.BigDecimal;

public abstract class AbstractCommand implements Serializable{

    protected static final long serialVersionUID = 14L;
    protected int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
