
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
    
    private List<Object[]> parametersForDtBoolean() {
        return asList(
                new Object[] {true},
                new Object[] {false}
        );
    }

    /**
     * Test single argument constructor method, of class DtBoolean.
     * @param value - value new DtBoolean will be created from
     */
    @Test
    @Parameters(method = "parametersForDtBoolean")
    public void testDtBoolean(boolean value) {
        @SuppressWarnings("UnusedAssignment")
        DtBoolean instance = new DtBoolean(value);
    }

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[] {true, "true"},
                new Object[] {false, "false"}
        );
    }

    /**
     * Test of getValue method, of class DtBoolean.
     * @param value - initialisation value for DtBoolean object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetStringValue(boolean value, String expectedResult) {
        DtBoolean instance = new DtBoolean(value);
        String result = instance.toStringValue();
        assertEquals("Incorrect getValue in DtBoolean", expectedResult, result);
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
        DtBoolean instance = new DtBoolean(value);
        String result = instance.toString();
        assertEquals("Incorrect toString in DtBoolean", expectedResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[] {true, (Object) null, false},
                new Object[] {true, new DtBoolean(true), true},
                new Object[] {true, new DtBoolean(false), false}
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
        DtBoolean instance = new DtBoolean(value);
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
        DtBoolean instance1 = new DtBoolean(value1);
        DtBoolean instance2 = new DtBoolean(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }
}
