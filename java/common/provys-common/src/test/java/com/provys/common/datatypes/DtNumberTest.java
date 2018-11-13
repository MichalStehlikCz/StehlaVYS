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

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit test class for DtNumber
 *
 * @author micha
 */
@RunWith(JUnitParamsRunner.class)
public class DtNumberTest {

    private List<Object[]> parametersForOf() {
        return asList(
                new Object[]{(BigDecimal) null, true},
                new Object[]{BigDecimal.valueOf(123456789.01234567), false},
                new Object[]{new BigDecimal(12345678911234L), false}
        );
    }

    /**
     * Test single argument constructor method, of class DtNumber.
     *
     * @param value - value new DtNumber will be created from
     * @param failNullValue - indicates creation should fail with
     * NullValueNotSupportedException
     */
    @Test
    @Parameters(method = "parametersForOf")
    public void testOf(BigDecimal value, boolean failNullValue) {
        @SuppressWarnings("UnusedAssignment")
        DtNumber instance;
        try {
            instance = DtNumber.of(value);
            if (failNullValue) {
                fail("Creation should have failed with NullValue exception");
            }
        } catch (Dt.NullValueNotSupportedException e) {
            if (!failNullValue) {
                fail("Creation should not have raised NullValue exception");
            }
        }
    }

    private List<Object[]> parametersForOfInt() {
        return asList(
                new Object[]{12345},
                new Object[]{123456789}
        );
    }

    /**
     * Test of int of initiator method of class DtNumber.
     *
     * @param value - value new DtNumber will be created from
     */
    @Test
    @Parameters(method = "parametersForOfInt")
    public void testOfInt(int value) {
        DtNumber instance = DtNumber.of(value);
        assertEquals("Value created from int does not match",
                 BigDecimal.valueOf(value), instance.getValue());
    }

    private List<Object[]> parametersForOfDouble() {
        return asList(
                new Object[]{12345.12345},
                new Object[]{123456789.01234567},
                new Object[]{12345678911234.0}
        );
    }

    /**
     * Test of float of initiator method of class DtNumber.
     *
     * @param value - value new DtNumber will be created from
     */
    @Test
    @Parameters(method = "parametersForOfDouble")
    public void testOfDouble(double value) {
        DtNumber instance = DtNumber.of(value);
        assertEquals("Value created from double does not match",
                 BigDecimal.valueOf(value), instance.getValue());
    }

    private List<Object[]> parametersForParseString() {
        return asList(
                new Object[]{(String) null, null, true},
                new Object[]{"", null, true},
                new Object[]{"123456789.01234567",
                     BigDecimal.valueOf(123456789.01234567), false},
                new Object[]{"12345678911234", new BigDecimal(12345678911234L),
                     false}
        );
    }

    /**
     * Test parseString method of class DtNumber.
     *
     * @param stringValue - String value DtNumber will be created from
     * @param value - value new DtNumber should get
     * @param failNullValue - indicates creation should fail with
     * NullValueNotSupportedException
     */
    @Test
    @Parameters(method = "parametersForParseString")
    public void testParseString(String stringValue, BigDecimal value,
             boolean failNullValue) {
        try {
            DtNumber instance = DtNumber.parseString(stringValue);
            assertFalse("Creation should have failed with NullValue exception",
                     failNullValue);
            assertEquals("Parsed string and value differ", value,
                     instance.getValue());
        } catch (DtNumber.NullValueNotSupportedException e) {
            assertTrue("Creation should not have raised NullValue exception",
                     failNullValue);
        }
    }

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    BigDecimal.valueOf(123456789.01234567)},
                new Object[]{new BigDecimal(12345678911234L),
                    new BigDecimal(12345678911234L)}
        );
    }

    /**
     * Test of getValue method, of class DtNumber.
     *
     * @param value - initialisation value for DtNumber object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(BigDecimal value, BigDecimal expectedResult) {
        DtNumber instance = DtNumber.of(value);
        BigDecimal result = instance.getValue();
        assertEquals("Incorrect getValue in DtNumber", expectedResult, result);
    }

    private List<Object[]> parametersForToStringValue() {
        return asList(
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    "123456789.01234567"},
                new Object[]{new BigDecimal(12345678911234L),
                    "12345678911234"}
        );
    }

    /**
     * Test of toStringValue method, of class DtNumber.
     *
     * @param value - initialisation value for DtNumber object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForToStringValue")
    public void testToStringValue(BigDecimal value, String expectedResult) {
        DtNumber instance = DtNumber.of(value);
        String result = instance.toStringValue();
        assertEquals("Incorrect toStringValue in DtNumber", expectedResult,
                result);
    }

    private List<Object[]> parametersForToString() {
        return asList(
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    "123456789.01234567"},
                new Object[]{new BigDecimal(12345678911234L),
                    "12345678911234"}
        );
    }

    /**
     * Test of toString method, of class DtNumber.
     *
     * @param value - initialisation value for DtNumber object
     * @param expectedResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(BigDecimal value, String expectedResult) {
        DtNumber instance = DtNumber.of(value);
        String result = instance.toString();
        assertEquals("Incorrect toString in DtNumber", expectedResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    (Object) null, false},
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    DtNumber.of(BigDecimal.valueOf(123456789.01234567)),
                    true},
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    DtNumber.of(BigDecimal.valueOf(123456789.01234568)),
                    false},
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    DtNumber.of(new BigDecimal(123456789)), false}
        );
    }

    /**
     * Test of equals method, of class DtNumber.
     *
     * @param value - initialisation value for compared DtNumber object
     * @param compareTo - Object to be compared against
     * @param expectedResult - expected comparison result
     */
    @Test
    @Parameters(method = "parametersForEquals")
    public void testEquals(BigDecimal value, Object compareTo,
            boolean expectedResult) {
        DtNumber instance = DtNumber.of(value);
        boolean result = instance.equals(compareTo);
        assertEquals("Equals method returned incorrect result", expectedResult,
                result);
    }

    private List<Object[]> parametersForHashCode() {
        return asList(
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    BigDecimal.valueOf(123456789.01234567)},
                new Object[]{new BigDecimal(12345678911234L),
                    BigDecimal.valueOf(123456789.01234567)}
        );
    }

    /**
     * Test of hashCode method, of class DtNumber. Verifies, that same hashCode
     * is produced when two instances of DtNumber are created and equals returns
     * true. Also asserts if values that not equal produce same hashcodes, even
     * though this is strictly speaking not an error, just indication that hash
     * function might not be good enough. In that case it might be useful to
     * verify, that on other data hashes are different and modify test data
     *
     * @param value1 - initialisation value for the first DtNumber hashCode is
     * calculated for
     * @param value2 - initialisation value for the second DtNumber hashCode is
     * calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(BigDecimal value1, BigDecimal value2) {
        DtNumber instance1 = DtNumber.of(value1);
        DtNumber instance2 = DtNumber.of(value2);
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
                new Object[]{BigDecimal.valueOf(123456789.01234567),
                    123456789.01234567},
                new Object[]{new BigDecimal(12345678911234L),
                    12345678911234.0}
        );
    }

    /**
     * Test of getDouble method, of class DtNumber.
     *
     * @param value is used to initialise test instance of DtNumber
     * @param expectedResult is compared against getDouble function result
     */
    @Test
    @Parameters(method = "parametersForGetDouble")
    public void testGetDouble(BigDecimal value, double expectedResult) {
        DtNumber instance = DtNumber.of(value);
        double result = instance.getDouble();
        assertEquals(expectedResult, result, 0.00000000000001);
    }

}
