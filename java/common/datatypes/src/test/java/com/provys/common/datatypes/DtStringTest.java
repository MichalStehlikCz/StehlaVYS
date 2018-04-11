/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stehlik
 */
public class DtStringTest {
    
    public DtStringTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test single argument constructor method, of class DtString.
     */
    @Test
    public void testDtString() {
        @SuppressWarnings("UnusedAssignment")
        DtString instance;
        try {
            instance = new DtString((String) null);
            fail("DtString creation passed with null value supplied");
        } catch (Dt.NullValueNotSupportedException e) {
        }
        try {
            instance = new DtString("");
            fail("DtString creation passed with empty string supplied");
        } catch (Dt.NullValueNotSupportedException e) {
        }
        instance = new DtString("abcdefghijkl");
    }

    /**
     * Test of getValue method, of class DtString.
     */
    @Test
    public void testGetValue() {
        DtString instance = new DtString("abcdefghijkl");
        String result = instance.getValue();
        assertEquals("Incorrect getValue on non-empty", "abcdefghijkl", result);
    }

    /**
     * Test of toString method, of class DtString.
     */
    @Test
    public void testToString() {
        DtString instance = new DtString("abcdefghijkl");
        String result = instance.toString();
        assertEquals("Incorrect toString on non-empty", "abcdefghijkl", result);
    }

    /**
     * Test of equals method, of class DtString.
     */
    @org.junit.Test
    public void testEquals() {
        Object nullObject = null;
        Object normal = new DtString("abcdefghijkl");
        DtString instance = new DtString("abcdefghijkl");
        boolean result = instance.equals(nullObject);
        assertFalse("Comparison with null value should be false", result);
        result = instance.equals(normal);
        assertTrue("Same DtString twice should equal", result);
        instance = new DtString("abcdefghijkx");
        result = instance.equals(normal);
        assertFalse("Two different DtStrings should not be equal", result);
    }

    /**
     * Test of hashCode method, of class DtString.
     */
    @org.junit.Test
    public void testHashCode() {
        DtString instance = new DtString("abcdefghijkl");
        DtString instance2 = new DtString("abcdefghijkl");
        int result = instance.hashCode();
        int result2 = instance2.hashCode();
        assertEquals("hashcodes of two same DtStrings should be the same", result, result2);
    }
    
}
