/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stehlik
 */
public class DtNameNmTest {
    
    public DtNameNmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test single argument constructor method, of class DtNameNm.
     */
    @Test
    public void testDtNameNm() {
        @SuppressWarnings("UnusedAssignment")
        DtNameNm instance;
        try {
            instance = new DtNameNm((String) null);
            fail("DtNameNm creation passed with null value supplied");
        } catch (Dt.NullValueNotSupportedException e) {
        }
        try {
            instance = new DtNameNm("");
            fail("DtNameNm creation passed with empty string supplied");
        } catch (Dt.NullValueNotSupportedException e) {
        }
        instance = new DtNameNm("abcdefghijkl");
        try {
            StringBuilder value = new StringBuilder();
            for (int i = 1;i <= 20;i++) {
                value.append("0123456789");
            }
            value.append("0");
            instance = new DtNameNm(value.toString());
            fail("DtNameNm creation passed with string longer than 200 characters");
        } catch (DtName.NameTooLongException e) {
        }
    }

}
