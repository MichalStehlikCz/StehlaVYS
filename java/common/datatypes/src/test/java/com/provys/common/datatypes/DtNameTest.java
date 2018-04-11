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
public class DtNameTest {
    
    public DtNameTest() {
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
     * Test single argument constructor method, of class DtName.
     */
    @Test
    public void testDtName() {
        @SuppressWarnings("UnusedAssignment")
        DtName instance;
        try {
            instance = new DtName((String) null);
            fail("DtName creation passed with null value supplied");
        } catch (Dt.NullValueNotSupportedException e) {
        }
        try {
            instance = new DtName("");
            fail("DtName creation passed with empty string supplied");
        } catch (Dt.NullValueNotSupportedException e) {
        }
        instance = new DtName("abcdefghijkl");
        try {
            StringBuilder value = new StringBuilder();
            for (int i = 1;i <= 20;i++) {
                value.append("0123456789");
            }
            value.append("0");
            instance = new DtName(value.toString());
            fail("DtName creation passed with string longer than 200 characters");
        } catch (DtName.NameTooLongException e) {
        }
    }

}
