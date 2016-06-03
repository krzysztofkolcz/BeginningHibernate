package com.kkolcz.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.kkolcz.dao.ProductCategoryDao;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.command.ProductCategoryCommand;
 
 
@Service("productCategoryService")
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService{
     
    @Autowired
    ProductCategoryDao dao;
     
    public ProductCategory findById(int id) {
        return dao.findById(id);
    }
 
    public ProductCategory findByName(String name){
        return dao.findByName(name);
    }
 
    public List<ProductCategory> findAllProductCategories() {
        return dao.findAllProductCategories();
    }

    public void addProductCategory(ProductCategoryCommand productCategoryCommand){
        ProductCategory productCategory = new ProductCategory(productCategoryCommand);
        dao.save(productCategory);
    }

    public void updateProductCategory(ProductCategoryCommand productCategoryCommand){
        ProductCategory productCategory =  dao.findById(productCategoryCommand.getId());
        productCategory.setName(productCategoryCommand.getName()); 
        if(!nameExistExceptId(productCategoryCommand.getName(),productCategoryCommand.getId())){
            dao.save(productCategory);
        }
    }

    public boolean nameExist(String name){
        ProductCategory productCategory = dao.findByName(name);
        if (productCategory != null) {
            return true;
        }
        return false;
    }


    public boolean nameExistExceptId(String name,int id) {
        List<ProductCategory> productCategories = dao.findByNameExceptId(name,id);
        if(productCategories != null && productCategories.size()>0){
          return true;
        }
        return false;
    }

}
