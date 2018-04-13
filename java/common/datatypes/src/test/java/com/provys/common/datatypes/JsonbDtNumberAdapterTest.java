/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.math.BigDecimal;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.logging.Logger;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author stehlik
 * Test class for JsonbDtNumberAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtNumberAdapterTest extends
        JsonbDtAdapterTest<DtNumber, BigDecimal, JsonbDtNumberAdapter> {
    
    /**
     *
     */
    public JsonbDtNumberAdapterTest() {
        adapter = new JsonbDtNumberAdapter();
    }
    
    private List<Object[]> parametersForAdapter() {
        return asList(new Object[] {new DtNumber(new BigDecimal(123_456_789))
                        , new BigDecimal(123_456_789)}
                , new Object[] {new DtNumber(new BigDecimal(123456789.01234567))
                        , new BigDecimal(123456789.01234567)}
        );
    }
    private static final Logger LOG = Logger.getLogger(JsonbDtNumberAdapterTest.class.getName());

}
