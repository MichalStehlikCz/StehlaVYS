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
 * @author micha
 */
public class DtUidTest {
    
    public DtUidTest() {
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
     * Test single argument constructor method, of class DtUid.
     */
    @Test
    public void testDtUid() {
        @SuppressWarnings("UnusedAssignment")
        DtUid instance = new DtUid((String) null);
        instance = new DtUid("123456789");
        try {
            instance = new DtUid("123a456");
            fail("Uid did not reject non-numeric value");
        }
        catch (DtUid.DtUidNotNumberException e) {
        }
    }

    /**
     * Test of getValue method, of class DtUid.
     */
    @Test
    public void testGetValue() {
        DtUid instance = new DtUid((String) null);
        String result = instance.getValue();
        assertEquals("getValue on null Uid must be null string", (String) null, result);
        instance = new DtUid("123456789");
        result = instance.getValue();
        assertEquals("Incorrect getValue on non-empty", "123456789", result);
    }

    /**
     * Test of toString method, of class DtUid.
     */
    @Test
    public void testToString() {
        DtUid instance = new DtUid((String) null);
        String result = instance.toString();
        assertEquals("toString on null Uid must be empty string", "", result);
        instance = new DtUid("123456789");
        result = instance.toString();
        assertEquals("Incorrect toString on non-empty", "123456789", result);
    }

    /**
     * Test of equals method, of class DtUid.
     */
    @org.junit.Test
    public void testEquals() {
        Object nullObject = null;
        Object nullUid = new DtUid(null);
        Object normalUid = new DtUid("123456789");
        DtUid instance = new DtUid(null);
        boolean result = instance.equals(nullObject);
        assertFalse("Comparison with null value should be false", result);
        result = instance.equals(nullUid);
        assertTrue("Comparison of two null DtUids not equal", result);
        result = instance.equals(normalUid);
        assertFalse("Comparison of null and not null Uid should be false", result);
        instance = new DtUid("123456789");
        result = instance.equals(nullObject);
        assertFalse("Comparison with null value should be false", result);
        result = instance.equals(nullUid);
        assertFalse("Not null and null Uids should not equal", result);
        result = instance.equals(normalUid);
        assertTrue("Same Uid twice should equal", result);
        instance = new DtUid("234567890");
        result = instance.equals(normalUid);
        assertFalse("Two different Uids should not be equal", result);
    }

    /**
     * Test of hashCode method, of class DtUid.
     */
    @org.junit.Test
    public void testHashCode() {
        DtUid instance = new DtUid(null);
        DtUid instance2 = new DtUid(null);
        int result = instance.hashCode();
        int result2 = instance2.hashCode();
        assertEquals("hashcodes of two null Uids should be same", result, result2);
        instance = new DtUid("123456789");
        instance2 = new DtUid("123456789");
        result = instance.hashCode();
        result2 = instance2.hashCode();
        assertEquals("hashcodes of two same Uids should be same", result, result2);
    }
    
}
