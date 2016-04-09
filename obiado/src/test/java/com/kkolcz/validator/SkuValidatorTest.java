package com.kkolcz.validator;

import com.kkolcz.command.ProductCommand;
import com.kkolcz.model.*;
import com.kkolcz.validator.*;


import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;
import java.util.HashSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.math.BigDecimal;

public class SkuValidatorTest {

    private static Validator validator;
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatorTest.class);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    private ProductCommand createProductCommand(){
        ProductCommand product = new ProductCommand();
        product.setId(1);
        product.setName("Schabowy");
        product.setPrice("18.80");
        product.setSku("123-123-123");
        product.setState(State.ACTIVE.getState());
        product.setActive(true);

        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1);
        productCategory.setName("4 jelenie");
        productCategories.add(productCategory);
        product.setProductCategories(productCategories);

        return product;
    }

    @Test
    public void validSkuTest()
    {

        ProductCommand product = createProductCommand();
        Set<ConstraintViolation<ProductCommand>> violations = validator.validate(product);
        String message = "";
        for(ConstraintViolation<ProductCommand> violation : violations) {
            message = "Message:- " + violation.getMessage() + " Path:- "+ violation.getPropertyPath();
            logger.info("info :" + message);
        }
        assertEquals(violations.size(), 0);
    }

    @Test
    public void invalidSkuTest()
    {
        ProductCommand product = createProductCommand();
        product.setSku("1234-123-123"); 
        Set<ConstraintViolation<ProductCommand>> violations = validator.validate(product);
        String message = "";
        for(ConstraintViolation<ProductCommand> violation : violations) {
            message = "Message:- " + violation.getMessage() + " Path:- "+ violation.getPropertyPath();
            logger.info("info :" + message);
        }
        assertEquals(violations.size(), 1);

        assertThat( violations, contains(
            hasProperty("message", is("Invalid sku"))
        ));
    }
}

