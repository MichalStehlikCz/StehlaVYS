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

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit test class for DtUid
 *
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class DtUidTest {

    private List<Object[]> parametersForOf() {
        return asList(
                new Object[]{(Object) null, true, false, false},
                new Object[]{"", true, false, false},
                new Object[]{"abcdefgh", false, false, true},
                new Object[]{"12345678901234567890123456789012345678", false,
                    false, false},
                new Object[]{"123456789012345678901234567890123456789", false,
                    true, false},
                new Object[]{"123456789", false, false, false}
        );
    }

    /**
     * Test static method of, of class DtUid.
     *
     * @param value - value used for test creation of DtUid instance
     * @param failNullValue - indicates creation should fail with
     * NullValueNotSupportedException
     * @param failTooLong - indicates creation should fail with
     * UidTooLongException
     * @param failNotNumber - indicates creation should fail with
     * UidNotNumberException
     */
    @Test
    @Parameters(method = "parametersForOf")
    public void testOf(String value, boolean failNullValue,
            boolean failTooLong, boolean failNotNumber) {
        @SuppressWarnings("UnusedAssignment")
        DtUid instance;
        try {
            instance = DtUid.of(value);
            if (failNullValue) {
                fail("Creation should have failed with NullValue exception");
            }
            if (failTooLong) {
                fail("Creation should have failed with TooLong exception");
            }
            if (failNotNumber) {
                fail("Creation should have failed with NotNumber exception");
            }
        } catch (Dt.NullValueNotSupportedException e) {
            if (!failNullValue) {
                fail("Creation should not have raised NullValue exception");
            }
        } catch (DtUid.UidTooLongException e) {
            if (!failTooLong) {
                fail("Creation should not have raised TooLong exception");
            }
        } catch (DtUid.UidNotNumberException e) {
            if (!failNotNumber) {
                fail("Creation should not have raised NotNumber exception");
            }
        }
    }

    private List<Object[]> parametersForGetValue() {
        return asList(
                new Object[]{DtUid.of("12345678901234567890123456789012345678"),
                    "12345678901234567890123456789012345678"},
                new Object[]{DtUid.of("1234"), "1234"}
        );
    }

    /**
     * Test of getValue method, of class DtUid.
     *
     * @param value - initialisation value for DtUid object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(DtUid value, String expectedResult) {
        String result = value.getValue();
        assertEquals("Incorrect getValue in DtUid", expectedResult, result);
    }

    private List<Object[]> parametersForToString() {
        return asList(
                new Object[]{DtUid.of("12345678901234567890123456789012345678"),
                    "12345678901234567890123456789012345678"},
                new Object[]{DtUid.of("1234"), "1234"}
        );
    }

    /**
     * Test of toString method, of class DtUid.
     *
     * @param value - initialisation value for DtString object
     * @param expectedResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(DtUid value, String expectedResult) {
        String result = value.toString();
        assertEquals("Incorrect toString in DtUid", expectedResult, result);
    }

    private List<Object[]> parametersForEquals() {
        return asList(
                new Object[]{"0123456789", null, false},
                new Object[]{"0123456789", DtUid.of("0123456789"), true},
                new Object[]{"0123456789", DtUid.of("123456789"), false},
                new Object[]{"0123456789", DtVarchar.of("0123456789"), false}
        );
    }

    /**
     * Test of equals method, of class DtUid.
     *
     * @param value - initialisation value for compared DtString object
     * @param compareTo - Object to be compared against
     * @param expectedResult - expected comparison result
     */
    @Test
    @Parameters(method = "parametersForEquals")
    public void testEquals(String value, Object compareTo,
            boolean expectedResult) {
        DtUid instance = DtUid.of(value);
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
                new Object[]{"0123456789", "0123456789"},
                new Object[]{"0123456789", "123456789"}
        );
    }

    /**
     * Test of hashCode method, of class DtUid. Verifies, that same hashCode is
     * produced when two instances of DtBoolean are created and equals returns
     * true. Also asserts if values that not equal produce same hashcodes, even
     * though this is strictly speaking not an error, just indication that hash
     * function might not be good enough. In that case it might be useful to
     * verify, that on other data hashes are different and modify test data
     *
     * @param value1 - initialisation value for the first DtString hashCode is
     * calculated for
     * @param value2 - initialisation value for the second DtString hashCode is
     * calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(String value1, String value2) {
        DtUid instance1 = DtUid.of(value1);
        DtUid instance2 = DtUid.of(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be the same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }

}
