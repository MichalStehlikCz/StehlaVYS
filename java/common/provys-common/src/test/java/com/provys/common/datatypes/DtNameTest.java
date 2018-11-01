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
 * Unit test class for DtName
 *
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class DtNameTest {

    private List<Object[]> parametersForOf() {
        StringBuilder value200 = new StringBuilder(201);
        for (int i = 1; i <= 20; i++) {
            value200.append("0123456789");
        }
        StringBuilder value4000 = new StringBuilder(4001);
        for (int i = 1; i <= 400; i++) {
            value4000.append("0123456789");
        }
        return asList(
                new Object[]{(Object) null, true, false},
                new Object[]{"", true, false},
                new Object[]{"abcdefgh", false, false},
                new Object[]{value200.toString(), false, false},
                new Object[]{value200.append("0"), false, true},
                new Object[]{value4000.append("0"), false, true}
        );
    }

    /**
     * Test single argument constructor method, of class DtNameNm.
     *
     * @param value - value used for test creation of DtNameNm instance
     * @param failNullValue - indicates creation should fail with
     * NullValueNotSupportedException
     * @param failTooLong - indicates creation should fail with
     * NameTooLongException
     */
    @Test
    @Parameters(method = "parametersForOf")
    public void testOf(String value, boolean failNullValue,
             boolean failTooLong) {
        @SuppressWarnings("UnusedAssignment")
        DtName instance;
        try {
            instance = DtName.of(value);
            assertFalse("Creation should have failed with NullValue exception",
                     failNullValue);
            assertFalse("Creation should have failed with TooLong exception",
                     failTooLong);
        } catch (DtName.NullValueNotSupportedException e) {
            assertTrue("Creation should not have raised NullValue exception",
                     failNullValue);
        } catch (DtName.NameTooLongException e) {
            assertTrue("Creation should not have raised TooLong exception",
                     failTooLong);
        }
    }

}
