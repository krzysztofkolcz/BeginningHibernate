package com.kkolcz.validator;

/* import javax.validation.*;  */
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kkolcz.annotation.ValidSku;

public class SkuValidator implements ConstraintValidator<ValidSku, String> {  
    private Pattern pattern;
    private Matcher matcher;
    private static final String SKU_PATTERN = "^[A-Za-z0-9]{3}-[A-Za-z0-9]{3}-[A-Za-z0-9]{3}$";
    @Override
    public void initialize(ValidSku constraintAnnotation) {       
    }
    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context){   
        return (validateSku(sku));
    } 
    private boolean validateSku(String sku) {
        pattern = Pattern.compile(SKU_PATTERN);
        matcher = pattern.matcher(sku);
        return matcher.matches();
    }
}
