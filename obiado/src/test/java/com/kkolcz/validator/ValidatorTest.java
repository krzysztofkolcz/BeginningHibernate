package com.kkolcz.validator;

import com.kkolcz.command.UserCommand;
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

public class ValidatorTest {

    private static Validator validator;
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatorTest.class);

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
        user.setEmail("email@email.pl");

        HashSet<UserProfile> userProfiles = new HashSet<UserProfile>();
        UserProfile userProfile = new UserProfile();
        userProfile.setType(UserProfileType.REGISTERED.getUserProfileType());
        userProfiles.add(userProfile);
        user.setUserProfiles(userProfiles);

        Set<ConstraintViolation<UserCommand>> violations = validator.validate(user);
        String message = "";
        for(ConstraintViolation<UserCommand> violation : violations) {
            /* logger.debug("Message:- " + violation.getMessage()); */
            /* System.out.println("Message:- " + violation.getMessage()); */
            message = "Message:- " + violation.getMessage() + " Path:- "+ violation.getPropertyPath();
            /* logger.trace("trace :" + message); */
            /* logger.debug("debug :" + message); */
            logger.info("info :" + message);
            /* logger.warn("warn :" + message); */
            /* logger.error("error :" + message); */
        }
        assertEquals(violations.size(), 1);

        assertThat( violations, contains(
            hasProperty("message", is("Passwords don't match"))
        ));
    }
}
