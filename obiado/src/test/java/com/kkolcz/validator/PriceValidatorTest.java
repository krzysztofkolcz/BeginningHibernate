package com.kkolcz.validator;

import com.kkolcz.command.ProductCommand;
import com.kkolcz.model.*;
import com.kkolcz.validator.*;
import com.kkolcz.fixture.Create;


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

public class PriceValidatorTest {

    private static Validator validator;
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(PriceValidatorTest.class);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void validPriceTest()
    {
        Create create = new Create();
        ProductCommand product = create.createFilledProductCommand();
        Set<ConstraintViolation<ProductCommand>> violations = validator.validate(product);
        String message = "";
        for(ConstraintViolation<ProductCommand> violation : violations) {
            message = "Message:- " + violation.getMessage() + " Path:- "+ violation.getPropertyPath();
            logger.info("info :" + message);
        }
        assertEquals(violations.size(), 0);
    }

    @Test
    public void invalidPriceTest()
    {
        Create create = new Create();
        ProductCommand product = create.createFilledProductCommand();
        product.setPrice("ag9"); 
        Set<ConstraintViolation<ProductCommand>> violations = validator.validate(product);
        String message = "";
        for(ConstraintViolation<ProductCommand> violation : violations) {
            message = "Message:- " + violation.getMessage() + " Path:- "+ violation.getPropertyPath();
            logger.info("info :" + message);
        }
        assertEquals(violations.size(), 1);

        assertThat( violations, contains(
            hasProperty("message", is("Invalid price"))
        ));
    }
}

