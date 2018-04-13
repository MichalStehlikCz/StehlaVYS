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
 * Test class for JsonbDtNameAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtNameNmAdapterTest extends
        JsonbDtAdapterTest<DtNameNm, String, JsonbDtNameNmAdapter> {
    
    /**
     *
     */
    public JsonbDtNameNmAdapterTest() {
        adapter = new JsonbDtNameNmAdapter();
    }
    
    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[] {new DtNameNm("0123456789"), "0123456789"}
                , new Object[] {new DtNameNm("abcd"), "abcd"}
        );
    }
    private static final Logger LOG = Logger.getLogger(JsonbDtNameNmAdapterTest.class.getName());

}
