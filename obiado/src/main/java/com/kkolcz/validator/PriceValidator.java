package com.kkolcz.validator;

/* import javax.validation.*;  */
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kkolcz.annotation.ValidPrice;

public class PriceValidator implements ConstraintValidator<ValidPrice, String> {  
    private Pattern pattern;
    private Matcher matcher;
    private static final String PRICE_PATTERN = "^[0-9]{1,9}\\.[0-9]{2}$"; 
    @Override
    public void initialize(ValidPrice constraintAnnotation) {       
    }
    @Override
    public boolean isValid(String price, ConstraintValidatorContext context){   
        return (validatePrice(price));
    } 
    private boolean validatePrice(String price) {
        pattern = Pattern.compile(PRICE_PATTERN);
        matcher = pattern.matcher(price);
        return matcher.matches();
    }
}
