package com.kkolcz.model;
 
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.math.BigDecimal;

import com.kkolcz.command.ProductCommand;
 
@Entity
@Table(name="product")
public class Product extends AbstractModel<ProductCommand> implements Model {
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
 
    @Column(name="name", nullable=false)
    private String name;

    @Column(name="price", nullable=false, precision = 8,scale = 2)
    private BigDecimal price;

    @Column(name="active", nullable=false)
    private boolean active;

    @Column(name="sku", nullable=false)
    private String sku;

    @Column(name="state", nullable=false)
    private String state=State.ACTIVE.getState();

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "product_product_category", 
             joinColumns = { @JoinColumn(name = "product_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "product_category_id") })
    private Set<ProductCategory> productCategories = new HashSet<ProductCategory>();

    @Override
    public void fillDataFromCommandObject(ProductCommand command){
        this.id = command.getId();
        this.name = command.getName();
        this.price = new BigDecimal(command.getPrice());
        this.sku = command.getSku();
        this.active = command.isActive();
        this.state = command.getState();
        this.productCategories = command.getProductCategories(); 
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
 
    public BigDecimal getPrice() {
        return price;
    }
 
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
 
 
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
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Product))
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Product [id=" + id + ", name =" + name 
                + ", sku =" + sku + ", price =" + price 
                + ", active =" +  String.valueOf(active) + ", productCategories=" + productCategories  +"]";
    }
     
}
