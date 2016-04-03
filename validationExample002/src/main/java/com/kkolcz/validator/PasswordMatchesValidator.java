package com.kkolcz.validator;

import com.kkolcz.command.UserCommand;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.lang.Object;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {   
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {       
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){   
        UserCommand user = (UserCommand) obj;
        System.out.println("============================");
        System.out.println("PasswordMatchesValidator");
        System.out.println("============================");
        return user.getPassword().equals(user.getMatchingPassword());    
    }     
}
