/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.util.Optional;
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
public class DtOptIntegerTest {
    
    public DtOptIntegerTest() {
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
     * Test of empty method, of class DtOptInteger.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.empty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of of method, of class DtOptInteger.
     */
    @Test
    public void testOf_Integer() {
        System.out.println("of");
        Integer value = null;
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.of(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ofNullable method, of class DtOptInteger.
     */
    @Test
    public void testOfNullable_Integer() {
        System.out.println("ofNullable");
        Integer value = null;
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.ofNullable(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of of method, of class DtOptInteger.
     */
    @Test
    public void testOf_Optional() {
        System.out.println("of");
        Optional<Integer> value = null;
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.of(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of of method, of class DtOptInteger.
     */
    @Test
    public void testOf_DtInteger() {
        System.out.println("of");
        DtInteger value = null;
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.of(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ofNullable method, of class DtOptInteger.
     */
    @Test
    public void testOfNullable_DtInteger() {
        System.out.println("ofNullable");
        DtInteger value = null;
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.ofNullable(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseString method, of class DtOptInteger.
     */
    @Test
    public void testParseString() {
        System.out.println("parseString");
        String value = "";
        DtOptInteger expResult = null;
        DtOptInteger result = DtOptInteger.parseString(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class DtOptInteger.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        DtOptInteger.register();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validatePrecision method, of class DtOptInteger.
     */
    @Test
    public void testValidatePrecision() {
        System.out.println("validatePrecision");
        Optional<Integer> precision = null;
        Optional<Integer> expResult = null;
        Optional<Integer> result = DtOptInteger.validatePrecision(precision);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateScale method, of class DtOptInteger.
     */
    @Test
    public void testValidateScale() {
        System.out.println("validateScale");
        Optional<Short> scale = null;
        Optional<Short> expResult = null;
        Optional<Short> result = DtOptInteger.validateScale(scale);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eligibleForSqlType method, of class DtOptInteger.
     */
    @Test
    public void testEligibleForSqlType() {
        System.out.println("eligibleForSqlType");
        int sqlType = 0;
        Optional<Integer> precision = null;
        Optional<Short> scale = null;
        boolean isNullable = false;
        String name = "";
        int expResult = 0;
        int result = DtOptInteger.eligibleForSqlType(sqlType, precision, scale, isNullable, name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toStringValue method, of class DtOptInteger.
     */
    @Test
    public void testToStringValue() {
        System.out.println("toStringValue");
        DtOptInteger instance = null;
        String expResult = "";
        String result = instance.toStringValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DtOptInteger.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DtOptInteger instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DtOptInteger.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object secondObject = null;
        DtOptInteger instance = null;
        boolean expResult = false;
        boolean result = instance.equals(secondObject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class DtOptInteger.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DtOptInteger instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDouble method, of class DtOptInteger.
     */
    @Test
    public void testGetDouble() {
        System.out.println("getDouble");
        DtOptInteger instance = null;
        Optional<Double> expResult = null;
        Optional<Double> result = instance.getDouble();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
