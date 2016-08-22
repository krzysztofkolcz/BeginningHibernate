package com.kkolcz.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkolcz.model.Product; 
import com.kkolcz.service.ProductService; 
import com.kkolcz.command.ProductCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* http://stackoverflow.com/questions/12146298/spring-mvc-how-to-perform-validation */
@Component
public class ProductValidator implements Validator {

  private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProductValidator.class);
  @Autowired private ProductService productService;

  @Override
  public boolean supports(Class clazz) {
    return Product.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors result) {
    ProductCommand productCommand = (ProductCommand) target;

    logger.error("validate "+productCommand);
    boolean skuExists = productService.skuExists(productCommand.getSku());
    if(skuExists){
      logger.error("error: there is already product with entered sku (skuExists)");
        result.rejectValue("sku", "message.skuError");
    }
  }
}
