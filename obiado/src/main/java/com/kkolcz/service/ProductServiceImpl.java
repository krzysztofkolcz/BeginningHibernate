package com.kkolcz.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.kkolcz.dao.ProductDaoImpl;
import com.kkolcz.dao.ProductDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.model.Product;
import com.kkolcz.command.ProductCommand;
 
 
@Service("productService")
@Transactional
public class ProductServiceImpl extends AbstractService<Product,ProductCommand,AbstractDao> implements ProductService{
     
    @Autowired ProductDao dao;
     
    @Override
    public boolean unique(ProductCommand command){
        boolean unique = !skuExistsExceptId(command.getSku(),command.getId()); /* && !nameExistExceptId(command.getName(),command.getId()) - zakładam, że nazwa może się powtarzać.  */
        return unique;
    }


    public boolean skuExists(String sku){
        Product element = (Product)dao.findBySku(sku);
        if (element != null) {
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
