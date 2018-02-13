package com.kkolcz;

//import org.junit.jupiter.api.Test;


import com.kkolcz.test.MyClass;
import org.junit.Test;
import static org.junit.Assert.*;

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyClassTest {

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        MyClass tester = new MyClass(); // MyClass is tested

        // assert statements
        assertEquals(0, tester.multiply(10, 0));
        assertEquals(0, tester.multiply(0, 10) );
        assertEquals(0, tester.multiply(0, 0));
    }
}
