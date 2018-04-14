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
 * Unit test class for DtString
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class DtStringTest {

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[] {"string", "string"},
                new Object[] {"0123456789", "0123456789"},
                new Object[] {"   abc   ", "   abc   "}
        );
    }

    /**
     * Test of getValue method, of class DtString.
     * @param value - initialisation value for DtString object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(String value, String expectedResult) {
        DtString instance = new DtStringImpl(value);
        String result = instance.getValue();
        assertEquals("Incorrect getValue in DtString", expectedResult, result);
    }

    private List<Object[]> parametersForToString() {
        return asList(
                new Object[] {"string", "string"},
                new Object[] {"0123456789", "0123456789"},
                new Object[] {"   abc   ", "   abc   "}
        );
    }

    /**
     * Test of toString method, of class DtString.
     * @param value - initialisation value for DtString object
     * @param expectedResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(String value, String expectedResult) {
        DtString instance = new DtStringImpl(value);
        String result = instance.toString();
        assertEquals("Incorrect toString in DtString", expectedResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[] {"abcdefghijkl", null, false},
                new Object[] {"abcdefghijkl", new DtStringImpl("abcdefghijkl"),
                    true},
                new Object[] {"abcdefghijkl", new DtStringImpl("0123456789"),
                    false},
                new Object[] {"0123456789", new DtStringImpl("123456789"),
                    false},
                new Object[] {"abcdefghijkl", new DtVarchar("abcdefghijkl"),
                    false}
        );
    }

    /**
     * Test of equals method, of class DtString.
     * @param value - initialisation value for compared DtString object
     * @param compareTo - Object to be compared against
     * @param expectedResult - expected comparison result
     */
    @Test
    @Parameters(method = "parametersForEquals")
    public void testEquals(String value, Object compareTo, boolean expectedResult) {
        DtString instance = new DtStringImpl(value);
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
                new Object[] {"abcdefghijkl", "abcdefghijkl"},
                new Object[] {"abcdefghijkl", "0123456789"},
                new Object[] {"0123456789", "123456789"},
                new Object[] {"abcdefghijkl", "abcdefghijkl"}
        );
    }

    /**
     * Test of hashCode method, of class DtString.
     * Verifies, that same hashCode is produced when two instances of DtBoolean
     * are created and equals returns true. Also asserts if values that not
     * equal produce same hashcodes, even though this is strictly speaking not
     * an error, just indication that hash function might not be good enough.
     * In that case it might be useful to verify, that on other data hashes are
     * different and modify test data
     * @param value1 - initialisation value for the first DtString hashCode is calculated for
     * @param value2 - initialisation value for the second DtString hashCode is calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(String value1, String value2) {
        DtString instance1 = new DtStringImpl(value1);
        DtString instance2 = new DtStringImpl(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be the same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }

    /**
     * Dummy ancestor used as proxy for testing abstract class DtString
     */
    static public class DtStringImpl extends DtString {

        private static final long serialVersionUID = 1L;

        /**
         * Proxy for single argument constructor of base class
         * @param value to which newly created string object will be initiated
         */
        public DtStringImpl(String value) {
            super(value);
        }
    }

}
