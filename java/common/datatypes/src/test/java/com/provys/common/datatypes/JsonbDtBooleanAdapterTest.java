/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author stehlik
 * Test class for JsonbDtBooleanAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtBooleanAdapterTest extends
        JsonbDtAdapterTest<DtBoolean, Boolean, JsonbDtBooleanAdapter> {
    
    /**
     *
     */
    public JsonbDtBooleanAdapterTest() {
        adapter = new JsonbDtBooleanAdapter();
    }
    
    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[] {new DtBoolean(true), true}
                , new Object[] {new DtBoolean(false), false}
        );
    }
    private static final Logger LOG = Logger.getLogger(JsonbDtBooleanAdapterTest.class.getName());

}
