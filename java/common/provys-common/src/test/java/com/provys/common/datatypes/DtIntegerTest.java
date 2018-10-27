/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test class for DtInteger
 *
 * @author micha
 */
@RunWith(JUnitParamsRunner.class)
public class DtIntegerTest {

    private List<Object[]> parametersForOf() {
        return asList(
                new Object[]{12345},
                new Object[]{-12345}
        );
    }

    /**
     * Test single argument constructor method, of class DtInteger.
     *
     * @param value - value new DtInteger will be created from
     */
    @Test
    @Parameters(method = "parametersForOf")
    public void testOf(int value) {
        @SuppressWarnings("UnusedAssignment")
        DtInteger instance = DtInteger.of(value);
    }

    private List<Object[]> parametersForOfString() {
        return asList(
                new Object[]{"12345", 12345},
                new Object[]{"-12345", -12345}
        );
    }

    /**
     * Test single argument constructor method, of class DtInteger.
     *
     * @param value - value new DtInteger will be created from
     * @param compareTo - int value result will be compared to
     */
    @Test
    @Parameters(method = "parametersForOfString")
    public void testOf(String value, int compareTo) {
        DtInteger instance = DtInteger.of(value);
        assertEquals(instance.getValue(), compareTo);
    }

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[]{12345, 12345},
                new Object[]{-12345, -12345}
        );
    }

    /**
     * Test of getValue method, of class DtInteger.
     *
     * @param value - initialisation value for DtInteger object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(int value, int expectedResult) {
        DtInteger instance = DtInteger.of(value);
        int result = instance.getValue();
        assertEquals("Incorrect getValue in DtInteger", expectedResult, result);
    }

    private List<Object[]> parametersForToStringValue() {
        return asList(
                new Object[]{12345, "12345"},
                new Object[]{-12345, "-12345"}
        );
    }

    /**
     * Test of toStringValue method, of class DtInteger.
     *
     * @param value - initialisation value for DtInteger object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForToStringValue")
    public void testToStringValue(int value, String expectedResult) {
        DtInteger instance = DtInteger.of(value);
        String result = instance.toStringValue();
        assertEquals("Incorrect toStringValue in DtInteger", expectedResult,
                result);
    }

    private List<Object[]> parametersForToString() {
        return asList(
                new Object[]{12345, "12345"},
                new Object[]{-12345, "-12345"}
        );
    }

    /**
     * Test of toString method, of class DtInteger.
     *
     * @param value - initialisation value for DtInteger object
     * @param expectedResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(int value, String expectedResult) {
        DtInteger instance = DtInteger.of(value);
        String result = instance.toString();
        assertEquals("Incorrect toString in DtInteger", expectedResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[]{12345, (Object) null, false},
                new Object[]{12345, DtInteger.of(12345), true},
                new Object[]{12345, DtInteger.of(-12345), false}
        );
    }

    /**
     * Test of equals method, of class DtInteger.
     *
     * @param value - initialisation value for compared DtInteger object
     * @param compareTo - Object to be compared against
     * @param expectedResult - expected comparison result
     */
    @Test
    @Parameters(method = "parametersForEquals")
    public void testEquals(int value, Object compareTo,
            boolean expectedResult) {
        DtInteger instance = DtInteger.of(value);
        boolean result = instance.equals(compareTo);
        assertEquals("Equals method returned incorrect result", expectedResult,
                result);
    }

    private List<Object[]> parametersForHashCode() {
        return asList(
                new Object[]{12345, 12345},
                new Object[]{12345, -12345}
        );
    }

    /**
     * Test of hashCode method, of class DtInteger. Verifies, that same hashCode
     * is produced when two instances of DtInteger are created and equals
     * returns true. Also asserts if values that not equal produce same
     * hashcodes, even though this is strictly speaking not an error, just
     * indication that hash function might not be good enough. In that case it
     * might be useful to verify, that on other data hashes are different and
     * modify test data
     *
     * @param value1 - initialisation value for the first DtInteger hashCode is
     * calculated for
     * @param value2 - initialisation value for the second DtInteger hashCode is
     * calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(int value1, int value2) {
        DtInteger instance1 = DtInteger.of(value1);
        DtInteger instance2 = DtInteger.of(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }

    private List<Object[]> parametersForGetDouble() {
        return asList(
                new Object[]{12345, 12345.0},
                new Object[]{-12345, -12345.0}
        );
    }

    /**
     * Test of getDouble method, of class DtInteger.
     *
     * @param value is used to initialise test instance of DtInteger
     * @param expectedResult is compared against getDouble function result
     */
    @Test
    @Parameters(method = "parametersForGetDouble")
    public void testGetDouble(int value, double expectedResult) {
        DtInteger instance = DtInteger.of(value);
        double result = instance.getDouble();
        assertEquals(expectedResult, result, 0.00000000000001);
    }

}
