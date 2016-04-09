package com.kkolcz.annotation;

import com.kkolcz.validator.SkuValidator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, FIELD, ANNOTATION_TYPE}) 
@Retention(RUNTIME)
@Constraint(validatedBy = SkuValidator.class)
@Documented
public @interface ValidSku{   
  String message() default "Invalid sku";
  Class<?>[] groups() default {}; 
  Class<? extends Payload>[] payload() default {};
}
