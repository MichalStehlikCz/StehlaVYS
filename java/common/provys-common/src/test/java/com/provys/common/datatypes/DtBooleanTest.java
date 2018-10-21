
package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test class for DtBoolean
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class DtBooleanTest {
    
    private List<Object[]> parametersForFromStringValue() {
        return asList(
                new Object[] {"Y", DtBoolean.getTRUE(), false, false},
                new Object[] {"N", DtBoolean.getFALSE(), false, false},
                new Object[] {null, null, false, true},
                new Object[] {"true", null, true, false}
        );
    }

    /**
     * Test fromStringValue static method
     * @param value - string value new DtBoolean will be created from
     * @param expectedResult - expected result of conversion
     * @param failInvalidValue - indicates conversion should raise
     * InvalidStringValueException
     * @param failNullValue - indicates conversion should raise
     * NullStringValueException
     */
    @Test
    @Parameters(method = "parametersForFromStringValue")
    public void testFromStringValue(String value, DtBoolean expectedResult,
            boolean failInvalidValue, boolean failNullValue) {
        try {
            DtBoolean instance = DtBoolean.fromStringValue(value);
            if (failInvalidValue || failNullValue) {
                fail("Conversion of string value to DtBoolean should have failed");
            }
            assertSame("Incorrect provys string to DtBoolean", expectedResult,
                    instance);
        } catch (DtBoolean.InvalidStringValueException e) {
            if (!failInvalidValue) {
                fail("Failed to convert string to DtBoolean - invalid thrown");
            }
        } catch (DtBoolean.NullStringValueException e) {
            if (!failNullValue) {
                fail("Failed to convert string to DtBoolean - null thrown");
            }
        }
    }

    private List<Object[]> parametersForFromValue() {
        return asList(
                new Object[] {true, DtBoolean.getTRUE()},
                new Object[] {false, DtBoolean.getFALSE()}
        );
    }

    /**
     * Test fromValue static method
     * @param value - boolean value new DtBoolean will be created from
     * @param expectedResult - expected result of conversion
     */
    @Test
    @Parameters(method = "parametersForFromValue")
    public void testFromValue(boolean value, DtBoolean expectedResult) {
        DtBoolean instance = DtBoolean.fromValue(value);
        assertSame("Incorrect provys boolean to DtBoolean", expectedResult,
                instance);
    }

    private List<Object[]> parametersForFromString() {
        return asList(
                new Object[] {"true", DtBoolean.getTRUE(), false, false},
                new Object[] {"False", DtBoolean.getFALSE(), false, false},
                new Object[] {null, null, false, true},
                new Object[] {"1", DtBoolean.getTRUE(), false, false},
                new Object[] {"0", DtBoolean.getFALSE(), false, false},
                new Object[] {"Y", null, true, false}
        );
    }

    /**
     * Test fromString static method
     * @param value - string value new DtBoolean will be created from
     * @param expectedResult - expected result of conversion
     * @param failInvalidString - indicates conversion should throw
     * InvalidStringException
     * @param failNullString - indicates conversion should throw
     * NullStringException
     */
    @Test
    @Parameters(method = "parametersForFromString")
    public void testFromString(String value, DtBoolean expectedResult,
            boolean failInvalidString, boolean failNullString) {
        try {
            DtBoolean instance = DtBoolean.fromString(value);
            if (failInvalidString || failNullString) {
                fail("Conversion of string value to DtBoolean should have failed");
            }
            assertSame("Incorrect string to DtBoolean", expectedResult,
                    instance);
        } catch (DtBoolean.InvalidStringException e) {
            if (!failInvalidString) {
                fail("Failed to convert string to DtBoolean - invalid thrown");
            }
        } catch (DtBoolean.NullStringException e) {
            if (! failNullString) {
                fail("Failed to convert string to DtBoolean - null thrown");
            }
        }
    }

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[] {true, true},
                new Object[] {false, false}
        );
    }

    /**
     * Test of getValue method, of class DtBoolean.
     * @param value - initialisation value for DtBoolean object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(boolean value, boolean expectedResult) {
        DtBoolean instance = DtBoolean.fromValue(value);
        boolean result = instance.getValue();
        assertEquals("Incorrect getValue in DtBoolean", expectedResult, result);
    }

    private List<Object[]> parametersForToStringValue() {
        return asList(
                new Object[] {true, "Y"},
                new Object[] {false, "N"}
        );
    }

    /**
     * Test of toStringValue method, of class DtBoolean.
     * @param value - initialisation value for DtBoolean object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForToStringValue")
    public void testToStringValue(boolean value, String expectedResult) {
        DtBoolean instance = DtBoolean.fromValue(value);
        String result = instance.toStringValue();
        assertEquals("Incorrect toStringValue in DtBoolean", expectedResult, result);
    }

    private List<Object[]> parametersForToString() {
        return asList(
                new Object[] {true, "true"},
                new Object[] {false, "false"}
        );
    }

    /**
     * Test of toString method, of class DtBoolean.
     * @param value - initialisation value for DtBoolean object
     * @param expectedResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(boolean value, String expectedResult) {
        DtBoolean instance = DtBoolean.fromValue(value);
        String result = instance.toString();
        assertEquals("Incorrect toString in DtBoolean", expectedResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[] {true, (Object) null, false},
                new Object[] {true, DtBoolean.fromValue(true), true},
                new Object[] {true, DtBoolean.fromValue(false), false}
        );
    }

    /**
     * Test of equals method, of class DtBoolean.
     * @param value - initialisation value for compared DtBoolean object
     * @param compareTo - Object to be compared against
     * @param expectedResult - expected comparison result
     */
    @Test
    @Parameters(method = "parametersForEquals")
    public void testEquals(boolean value, Object compareTo, boolean expectedResult) {
        DtBoolean instance = DtBoolean.fromValue(value);
        boolean result = instance.equals(compareTo);
        if (expectedResult) {
            assertTrue("Equals method returned incorrect result (expected true)"
                    , result);
        } else {
            assertFalse("Equals method returned incorrect result (exp. false)"
                    , result);
        }
    }

    private List<Object[]> parametersForHashCode() {
        return asList(
                new Object[] {true, true},
                new Object[] {false, false},
                new Object[] {true, false}
        );
    }

    /**
     * Test of hashCode method, of class DtBoolean.
     * Verifies, that same hashCode is produced when two instances of DtBoolean
     * are created and equals returns true. Also asserts if values that not
     * equal produce same hashcodes, even though this is strictly speaking not
     * an error, just indication that hash function might not be good enough.
     * In that case it might be useful to verify, that on other data hashes are
     * different and modify test data
     * @param value1 - initialisation value for the first DtBoolean hashCode is calculated for
     * @param value2 - initialisation value for the second DtBoolean hashCode is calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(boolean value1, boolean value2) {
        DtBoolean instance1 = DtBoolean.fromValue(value1);
        DtBoolean instance2 = DtBoolean.fromValue(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }
}
