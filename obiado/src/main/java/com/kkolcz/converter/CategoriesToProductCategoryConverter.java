package com.kkolcz.converter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
 
import com.kkolcz.model.ProductCategory;
import com.kkolcz.service.ProductCategoryService;
 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class CategoriesToProductCategoryConverter implements Converter<Object, ProductCategory>{
 
    @Autowired ProductCategoryService productCategoryService ;
 
    /**
     * Gets ProductCategory by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public ProductCategory convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        ProductCategory category= productCategoryService.findById(id);
        System.out.println("Product category: "+category);
        return category;
    }
     
}
