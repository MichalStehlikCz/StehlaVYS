package com.provys.common.datatypes;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit test class for DtBoolean.
 *
 * @author stehlik
 */
public class DtBooleanTest {

    /**
     * Test of getTRUE method, of class DtBoolean.
     */
    @Test
    public void testGetTRUE() {
        System.out.println("getTRUE");
        DtBoolean result = DtBoolean.getTRUE();
        assertTrue("Returns value other than true", result.getValue());
    }

    /**
     * Test of getFALSE method, of class DtBoolean.
     */
    @Test
    public void testGetFALSE() {
        System.out.println("getFALSE");
        DtBoolean result = DtBoolean.getFALSE();
        assertFalse("Returns value other than false", result.getValue());
    }

    private List<Object[]> parametersForOf() {
        return asList(
                new Object[]{true, DtBoolean.getTRUE()},
                new Object[]{false, DtBoolean.getFALSE()}
        );
    }

    /**
     * Test of static method, of class DtBoolean.
     *
     * @param value - boolean value for value parameter
     * @param expResult - expected result of conversion
     */
    @Test
    @Parameters(method = "parametersForOf")
    public void testOf(boolean value, DtBoolean expResult) {
        System.out.println("of");
        DtBoolean result = DtBoolean.of(value);
        assertEquals(expResult, result);
    }

    private List<Object[]> parametersForFromStringValue() {
        return asList(
                new Object[]{"Y", DtBoolean.getTRUE(), false, false},
                new Object[]{"N", DtBoolean.getFALSE(), false, false},
                new Object[]{null, null, false, true},
                new Object[]{"true", null, true, false}
        );
    }

    /**
     * Test fromStringValue static method, of class DtBoolean.
     *
     * @param value - string value new DtBoolean will be created from
     * @param expResult - expected result of conversion
     * @param failInvalidValue - indicates conversion should raise
     * InvalidStringValueException
     * @param failNullValue - indicates conversion should raise
     * NullStringValueException
     */
    @Test
    @Parameters(method = "parametersForFromStringValue")
    public void testFromStringValue(String value, DtBoolean expResult,
            boolean failInvalidValue, boolean failNullValue) {
        System.out.println("fromStringValue");
        try {
            DtBoolean result = DtBoolean.fromStringValue(value);
            assertFalse("Should have failed"
                    , failInvalidValue || failNullValue);
            assertEquals(expResult, result);
        } catch (DtBoolean.InvalidStringValueException e) {
            assertTrue("Invalid value thrown", failInvalidValue);
        } catch (DtBoolean.NullValueNotSupportedException e) {
            assertTrue("Null value thrown", failNullValue);
        }
    }

    private List<Object[]> parametersForFromString() {
        return asList(
                new Object[]{"true", DtBoolean.getTRUE(), false, false},
                new Object[]{"False", DtBoolean.getFALSE(), false, false},
                new Object[]{null, null, false, true},
                new Object[]{"", null, false, true},
                new Object[]{"1", DtBoolean.getTRUE(), false, false},
                new Object[]{"0", DtBoolean.getFALSE(), false, false},
                new Object[]{"Y", null, true, false}
        );
    }

    /**
     * Test fromString static method, of class DtBoolean.
     *
     * @param value - string value new DtBoolean will be created from
     * @param expResult - expected result of conversion
     * @param failInvalidString - indicates conversion should throw
     * InvalidStringException
     * @param failNullString - indicates conversion should throw
     * NullStringException
     */
    @Test
    @Parameters(method = "parametersForFromString")
    public void testFromString(String value, DtBoolean expResult,
            boolean failInvalidString, boolean failNullString) {
        System.out.println("fromString");
        try {
            DtBoolean result = DtBoolean.fromString(value);
            assertFalse("Should have failed"
                    , failInvalidString || failNullString);
            assertEquals(expResult, result);
        } catch (DtBoolean.InvalidStringException e) {
            assertTrue("Invalid value thrown", failInvalidString);
        } catch (DtBoolean.NullValueNotSupportedException e) {
            assertTrue("Null value thrown", failNullString);
        }
    }

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[]{DtBoolean.of(true), true},
                new Object[]{DtBoolean.of(false), false}
        );
    }

    /**
     * Test of getValue method, of class DtBoolean.
     *
     * @param instance - DtBoolean instance method will be run on
     * @param expResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(DtBoolean instance, boolean expResult) {
        System.out.println("getValue");
        boolean result = instance.getValue();
        assertEquals(expResult, result);
    }

    private List<Object[]> parametersForToStringValue() {
        return asList(
                new Object[]{DtBoolean.of(true), "Y"},
                new Object[]{DtBoolean.of(false), "N"}
        );
    }

    /**
     * Test of toStringValue method, of class DtBoolean.
     *
     * @param instance - initialisation value for DtBoolean object
     * @param expResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForToStringValue")
    public void testToStringValue(DtBoolean instance, String expResult) {
        System.out.println("toStringValue");
        String result = instance.toStringValue();
        assertEquals(expResult, result);
    }

    private List<Object[]> parametersForToString() {
        return asList(
                new Object[]{DtBoolean.of(true), "true"},
                new Object[]{DtBoolean.of(false), "false"}
        );
    }

    /**
     * Test of toString method, of class DtBoolean.
     *
     * @param instance - initialisation value for DtBoolean object
     * @param expResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(DtBoolean instance, String expResult) {
        System.out.println("toString");
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[]{true, (Object) null, false},
                new Object[]{true, DtBoolean.of(true), true},
                new Object[]{true, DtBoolean.of(false), false},
                new Object[]{true, DtOptBoolean.empty(), false},
                new Object[]{true, DtOptBoolean.of(true), true},
                new Object[]{true, DtOptBoolean.of(false), false},
                new Object[]{true, "false", false}
        );
    }

    /**
     * Test of equals method, of class DtBoolean.
     *
     * @param value - initialisation value for compared DtBoolean object
     * @param compareTo - Object to be compared against
     * @param expectedResult - expected comparison result
     */
    @Test
    @Parameters(method = "parametersForEquals")
    public void testEquals(boolean value, Object compareTo, boolean expectedResult) {
        DtBoolean instance = DtBoolean.of(value);
        boolean result = instance.equals(compareTo);
        if (expectedResult) {
            assertTrue("Equals method returned incorrect result (expected true)",
                     result);
        } else {
            assertFalse("Equals method returned incorrect result (exp. false)",
                     result);
        }
    }

    private List<Object[]> parametersForHashCode() {
        return asList(
                new Object[]{true, true},
                new Object[]{false, false},
                new Object[]{true, false}
        );
    }

    /**
     * Test of hashCode method, of class DtBoolean. Verifies, that same hashCode
     * is produced when two instances of DtBoolean are created and equals
     * returns true. Also asserts if values that not equal produce same
     * hashcodes, even though this is strictly speaking not an error, just
     * indication that hash function might not be good enough. In that case it
     * might be useful to verify, that on other data hashes are different and
     * modify test data
     *
     * @param value1 - initialisation value for the first DtBoolean hashCode is
     * calculated for
     * @param value2 - initialisation value for the second DtBoolean hashCode is
     * calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(boolean value1, boolean value2) {
        DtBoolean instance1 = DtBoolean.of(value1);
        DtBoolean instance2 = DtBoolean.of(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }
}
