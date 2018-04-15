/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;
import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test class for ConfObject
 * @author micha
 */
@RunWith(JUnitParamsRunner.class)
public class ConfObjectTest {
    
    private List<Object[]> parametersForGetId() {
        return asList(
                new Object[] {new DtUid("123456789"), new DtUid("123456789")},
                new Object[] {new DtUid("9876543210"), new DtUid("9876543210")}
        );
    }

    /**
     * Test of getId method, of class ConfObject.
     * @param id used to create test instance of ConfObject
     * @param expectedResult is compared with result of getId method
     */
    @Test
    @Parameters(method = "parametersForGetId")
    public void testGetId(DtUid id, DtUid expectedResult) {
        ConfObject instance = new ConfObjectImpl(id);
        DtUid result = instance.getId();
        assertEquals(expectedResult, result);
    }

    static public class ConfObjectImpl extends ConfObject {

        private static final long serialVersionUID = 1L;

        public ConfObjectImpl(DtUid id) {
            super(id);
        }
    }
    
}
