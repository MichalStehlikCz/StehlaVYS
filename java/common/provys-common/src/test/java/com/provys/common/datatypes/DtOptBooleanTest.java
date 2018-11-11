/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit test class for DtOptBoolean.
 * 
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class DtOptBooleanTest {
    
    /**
     * Test of getTRUE method, of class DtOptBoolean.
     */
    @Test
    public void testGetTRUE() {
        System.out.println("getTRUE");
        DtOptBoolean result = DtOptBoolean.getTRUE();
        assertTrue("Returns empty optional", result.isPresent());
        assertTrue("Returns value other than true", result.get());
    }

    /**
     * Test of getFALSE method, of class DtOptBoolean.
     */
    @Test
    public void testGetFALSE() {
        System.out.println("getFALSE");
        DtOptBoolean result = DtOptBoolean.getFALSE();
        assertTrue("Returns empty optional", result.isPresent());
        assertFalse("Returns value other than false", result.get());
    }

    /**
     * Test of empty method, of class DtOptBoolean.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        DtOptBoolean result = DtOptBoolean.empty();
        assertFalse("Does not returns empty optional", result.isPresent());
    }

    private List<Object[]> parametersForOf() {
        return asList(
                new Object[]{true, DtOptBoolean.getTRUE()},
                new Object[]{false, DtOptBoolean.getFALSE()}
        );
    }

    /**
     * Test of of method, of class DtOptBoolean.
     * 
     * @param value is boolean value supplied as parameter
     * @param expResult is expected DtBoolean object
     */
    @Test
    @Parameters(method = "parametersForOf")
    public void testOf(boolean value, DtOptBoolean expResult) {
        System.out.println("of");
        DtOptBoolean result = DtOptBoolean.of(value);
        assertEquals("Value does not match", expResult, result);
    }

    private List<Object[]> parametersForOfNullable() {
        return asList(
                new Object[]{Boolean.TRUE, DtOptBoolean.getTRUE()},
                new Object[]{Boolean.FALSE, DtOptBoolean.getFALSE()},
                new Object[]{(Boolean) null, DtOptBoolean.empty()}
        );
    }

    /**
     * Test of ofNullable method, of class DtOptBoolean.
     * @param value is value for Boolean value parameter
     * @param expResult is expected result
     */
    @Test
    @Parameters(method = "parametersForOfNullable")
    public void testOfNullable(Boolean value, DtOptBoolean expResult) {
        System.out.println("ofNullable");
        DtOptBoolean result = DtOptBoolean.ofNullable(value);
        assertEquals("Value does not match", expResult, result);
    }

    /**
     * Test of ofDtBoolean method, of class DtOptBoolean.
     */
    @Test
    public void testOfDtBoolean() {
        System.out.println("ofDtBoolean");
        DtBoolean value = null;
        DtOptBoolean expResult = null;
        DtOptBoolean result = DtOptBoolean.ofDtBoolean(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ofNullableDtBoolean method, of class DtOptBoolean.
     */
    @Test
    public void testOfNullableDtBoolean() {
        System.out.println("ofNullableDtBoolean");
        DtBoolean value = null;
        DtOptBoolean expResult = null;
        DtOptBoolean result = DtOptBoolean.ofNullableDtBoolean(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ofOptional method, of class DtOptBoolean.
     */
    @Test
    public void testOfOptional() {
        System.out.println("ofOptional");
        Optional<Boolean> value = null;
        DtOptBoolean expResult = null;
        DtOptBoolean result = DtOptBoolean.ofOptional(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of fromStringValue method, of class DtOptBoolean.
     */
    @Test
    public void testFromStringValue() {
        System.out.println("fromStringValue");
        String stringValue = "";
        DtOptBoolean expResult = null;
        DtOptBoolean result = DtOptBoolean.fromStringValue(stringValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fromString method, of class DtOptBoolean.
     */
    @Test
    public void testFromString() {
        System.out.println("fromString");
        String value = "";
        DtOptBoolean expResult = null;
        DtOptBoolean result = DtOptBoolean.fromString(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class DtOptBoolean.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        DtOptBoolean.register();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validatePrecision method, of class DtOptBoolean.
     */
    @Test
    public void testValidatePrecision() {
        System.out.println("validatePrecision");
        Optional<Integer> precision = null;
        Optional<Integer> expResult = null;
        Optional<Integer> result = DtOptBoolean.validatePrecision(precision);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toStringValue method, of class DtOptBoolean.
     */
    @Test
    public void testToStringValue() {
        System.out.println("toStringValue");
        DtOptBoolean instance = null;
        String expResult = "";
        String result = instance.toStringValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DtOptBoolean.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DtOptBoolean instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toSqlLiteral method, of class DtOptBoolean.
     */
    @Test
    public void testToSqlLiteral() {
        System.out.println("toSqlLiteral");
        DtOptBoolean instance = null;
        String expResult = "";
        String result = instance.toSqlLiteral();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DtOptBoolean.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object secondObject = null;
        DtOptBoolean instance = null;
        boolean expResult = false;
        boolean result = instance.equals(secondObject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class DtOptBoolean.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DtOptBoolean instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
