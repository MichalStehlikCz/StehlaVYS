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
public class DtNameTest {
    
    private Object[] parametersForDtName() {
        StringBuilder value200 = new StringBuilder();
        for (int i = 1;i <= 20;i++) {
            value200.append("0123456789");
        }
        StringBuilder value4000 = new StringBuilder();
        for (int i = 1;i <= 400;i++) {
            value4000.append("0123456789");
        }
        return $($((Object) null, true, false)
                , $("", true, false)
                , $("abcdefgh", false, false)
                , $(value200.toString(), false, false)
                , $(value200.append("0"), false, true)
                , $(value4000.append("0"), false, true)
        );
    }

    /**
     * Test single argument constructor method, of class DtNameNm.
     * @param value - value used for test creation of DtNameNm instance
     * @param failNullValue - indicates creation should fail with NullValueNotSupportedException
     * @param failTooLong - indicates creation should fail with NameTooLongException
     */
    @Test
    @Parameters(method = "parametersForDtName")
    public void testDtName(String value, boolean failNullValue
            , boolean failTooLong) {
        @SuppressWarnings("UnusedAssignment")
        DtName instance;
        try {
            instance = new DtName(value);
            if (failNullValue) {
                fail("Creation should have failed with NullValue exception");
            }
            if (failTooLong) {
                fail("Creation should have failed with TooLong exception");
            }
        } catch (Dt.NullValueNotSupportedException e) {
            if (!failNullValue) {
                fail("Creation should not have raised NullValue exception");
            }
        } catch (DtName.NameTooLongException e) {
            if (!failTooLong) {
                fail("Creation should not have raised TooLong exception");
            }
        }
    }
    private static final Logger LOG = Logger.getLogger(DtNameTest.class.getName());

}
