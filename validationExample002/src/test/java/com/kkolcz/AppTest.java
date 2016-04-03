package com.kkolcz;

import com.kkolcz.command.UserCommand;
import com.kkolcz.validator.*;


import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static Validator validator;
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppTest.class);

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void NotMatchingPassword()
    {
        UserCommand user = new UserCommand();
        user.setId(1);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setMatchingPassword("notMatchingPassword");
        user.setEmail("email");
        Set<ConstraintViolation<UserCommand>> violations = validator.validate(user);
        for(ConstraintViolation<UserCommand> violation : violations) {
            logger.debug("Message:- " + violation.getMessage());
        }
        assertEquals(violations.size(), 1);
    }
}
