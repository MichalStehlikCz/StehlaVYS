/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class DtStringTest {
    
    
    private Object[] parametersForGetValue() {
        return $(
                $("string", "string")
                , $("0123456789", "0123456789")
                , $("   abc   ", "   abc   ")
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

    private Object[] parametersForToString() {
        return $(
                $("string", "string")
                , $("0123456789", "0123456789")
                , $("   abc   ", "   abc   ")
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

    private Object[] parametersForEquals() {
        return $($("abcdefghijkl", null, false)
                , $("abcdefghijkl", new DtStringImpl("abcdefghijkl"), true)
                , $("abcdefghijkl", new DtStringImpl("0123456789"), false)
                , $("0123456789", new DtStringImpl("123456789"), false)
                , $("abcdefghijkl", new DtVarchar("abcdefghijkl"), false)
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

    private Object[] parametersForHashCode() {
        return $(
                $("abcdefghijkl", "abcdefghijkl")
                , $("abcdefghijkl", "0123456789")
                , $("0123456789", "123456789")
                , $("abcdefghijkl", "abcdefghijkl")
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
     *
     */
    public class DtStringImpl extends DtString {

        /**
         *
         * @param value
         */
        public DtStringImpl(String value) {
            super(value);
        }
    }

}
