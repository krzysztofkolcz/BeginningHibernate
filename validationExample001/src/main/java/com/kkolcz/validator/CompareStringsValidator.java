package com.kkolcz.validator;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.lang.Object;

public class CompareStringsValidator implements ConstraintValidator<CompareStrings, Object> {

    private String[] propertyNames;
    private StringComparisonMode comparisonMode;
    private boolean allowNull;

    @Override
    public void initialize(CompareStrings constraintAnnotation) {
        this.propertyNames = constraintAnnotation.propertyNames();
        this.comparisonMode = constraintAnnotation.matchMode();
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        boolean isValid = true;
        List<String> propertyValues = new ArrayList<String> (propertyNames.length);
        for(int i=0; i<propertyNames.length; i++) {
            String propertyValue = ConstraintValidatorHelper.getPropertyValue(String.class, propertyNames[i], target);
            if(propertyValue == null) {
                if(!allowNull) {
                    isValid = false;
                    break;
                }
            } else {
                propertyValues.add(propertyValue);
            }
        }

        if(isValid) {
            isValid = ConstraintValidatorHelper.isValid(propertyValues, comparisonMode);
        }

        if (!isValid) {
          /*
           * if custom message was provided, don't touch it, otherwise build the
           * default message
           */
          String message = context.getDefaultConstraintMessageTemplate();
          message = (message.isEmpty()) ?  ConstraintValidatorHelper.resolveMessage(propertyNames, comparisonMode) : message;

          context.disableDefaultConstraintViolation();
          ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(message);
          for (String propertyName : propertyNames) {
            NodeBuilderDefinedContext nbdc = violationBuilder.addNode(propertyName);
            nbdc.addConstraintViolation();
          }
        }    

        return isValid;
    }
}
