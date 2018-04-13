/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author stehlik
 * Test class for JsonbDtVarcharAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtVarcharAdapterTest extends
        JsonbDtAdapterTest<DtVarchar, String, JsonbDtVarcharAdapter> {
    
    /**
     *
     */
    public JsonbDtVarcharAdapterTest() {
        adapter = new JsonbDtVarcharAdapter();
    }
    
    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[] {new DtVarchar("0123456789"), "0123456789"},
                new Object[] {new DtVarchar("abcd"), "abcd"}
        );
    }

}
