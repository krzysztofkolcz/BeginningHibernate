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
        return dao.findByNaturalKey(name);
    }
 
    public List<ProductCategory> findAllProductCategories() {
        return dao.findAll("name");
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
        ProductCategory productCategory = dao.findByNaturalKey(name);
        if (productCategory != null) {
            return true;
        }
        return false;
    }

    /* updateUnique */
    public boolean nameExistExceptId(String name,int id) {
        ProductCategory productCategory = dao.findByNaturalKeyExceptId(name,id);
        if(productCategory != null){
          return true;
        }
        return false;
    }

}
