package com.kkolcz;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.kkolcz.model.*;
import com.kkolcz.validator.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void test() {
        User user = new User();
        user.setPassword("password");
        user.setConfirmPassword("paSSword");
        Set<ConstraintViolation<User>> violations = beanValidator.validate(user);
        for(ConstraintViolation<User> violation : violations) {
            logger.debug("Message:- " + violation.getMessage());
        }
        Assert.assertEquals(violations.size(), 1);
    }
}
