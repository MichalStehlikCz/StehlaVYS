/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class DtUidTest {
    
    private Object[] parametersForDtUid() {
        return $($((Object) null, true, false, false)
                , $("", true, false, false)
                , $("abcdefgh", false, false, true)
                , $("12345678901234567890123456789012345678", false, false
                        , false)
                , $("123456789012345678901234567890123456789", false, true
                        , false)
                , $("123456789", false, false, false)
        );
    }

    /**
     * Test single argument constructor method, of class DtUid.
     * @param value - value used for test creation of DtUid instance
     * @param failNullValue - indicates creation should fail with NullValueNotSupportedException
     * @param failTooLong - indicates creation should fail with UidTooLongException
     * @param failNotNumber - indicates creation should fail with UidNotNumberException
     */
    @Test
    @Parameters(method = "parametersForDtUid")
    public void testDtUid(String value, boolean failNullValue
            , boolean failTooLong, boolean failNotNumber) {
        @SuppressWarnings("UnusedAssignment")
        DtUid instance;
        try {
            instance = new DtUid(value);
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

    private Object[] parametersForGetValue() {
        return $(
                $("0123456789", "0123456789")
        );
    }

    /**
     * Test of getValue method, of class DtUid.
     * @param value - initialisation value for DtUid object
     * @param expectedResult - expected result of getValue method
     */
    @Test
    @Parameters(method = "parametersForGetValue")
    public void testGetValue(String value, String expectedResult) {
        DtUid instance = new DtUid(value);
        String result = instance.getValue();
        assertEquals("Incorrect getValue in DtUid", expectedResult, result);
    }

    private Object[] parametersForToString() {
        return $(
                $("0123456789", "0123456789")
        );
    }

    /**
     * Test of toString method, of class DtUid.
     * @param value - initialisation value for DtString object
     * @param expectedResult - expected result of toString method
     */
    @Test
    @Parameters(method = "parametersForToString")
    public void testToString(String value, String expectedResult) {
        DtUid instance = new DtUid(value);
        String result = instance.toString();
        assertEquals("Incorrect toString in DtUid", expectedResult, result);
    }

    private Object[] parametersForEquals() {
        return $($("0123456789", null, false)
                , $("0123456789", new DtUid("0123456789"), true)
                , $("0123456789", new DtUid("123456789"), false)
                , $("0123456789", new DtVarchar("0123456789"), false)
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
        DtUid instance = new DtUid(value);
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
                $("0123456789", "0123456789")
                , $("0123456789", "123456789")
        );
    }

    /**
     * Test of hashCode method, of class DtUid.
     * Verifies, that same hashCode is produced when two instances of DtBoolean
     * are created and equals returns true. Also asserts if values that not
     * equal produce same hashcodes, even though this is strictly speaking not
     * an error, just indication that hash function might not be good enough.
     * In that case it might be useful to verify, that on other data hashes are
     * different and modify test data
     * @param value1 - initialisation value for the first DtString hashCode is
     * calculated for
     * @param value2 - initialisation value for the second DtString hashCode is
     * calculated for
     */
    @Test
    @Parameters(method = "parametersForHashCode")
    public void testHashCode(String value1, String value2) {
        DtUid instance1 = new DtUid(value1);
        DtUid instance2 = new DtUid(value2);
        int result1 = instance1.hashCode();
        int result2 = instance2.hashCode();
        if (instance1.equals(instance2)) {
            assertEquals("Hashcodes should be the same", result1, result2);
        } else {
            assertNotEquals("Hashcodes should be different", result1, result2);
        }
    }
    private static final Logger LOG = Logger.getLogger(DtUidTest.class.getName());

}
