package com.kkolcz.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.kkolcz.dao.ProductDaoImpl;
import com.kkolcz.dao.ProductDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.model.Product;
import com.kkolcz.command.ProductCommand;
 
 
@Service("productService")
@Transactional
public class ProductServiceImpl extends AbstractService<Product,ProductCommand,ProductDao> implements ProductService{
     
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductDao productDao){
      super(productDao);
    } 

    @Override
    public boolean unique(ProductCommand command){
        boolean unique = !skuExistsExceptId(command.getSku(),command.getId()); /* && !nameExistExceptId(command.getName(),command.getId()) - zakładam, że nazwa może się powtarzać.  */
        return unique;
    }

    public boolean skuExists(String sku){
        List<Product> elements = (List<Product>)dao.findBySku(sku);
        if (elements != null && elements.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean skuExistsExceptId(String sku,int id){
        List<Product> elements = dao.findBySkuExceptId(sku,id);
        if(elements != null && elements.size()>0){
          return true;
        }
        return false;
    }

}
