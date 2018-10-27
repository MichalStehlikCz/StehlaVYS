/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author stehlik
 * Test class for JsonbDtUidAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtUidAdapterTest extends
        JsonbDtAdapterTest<DtUid, String, JsonbDtUidAdapter> {
    
    /**
     * Initializes adapter used to run tests
     */
    @Before
    public void setUp() {
        adapter = new JsonbDtUidAdapter();
    }
    
    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[] {DtUid.of("0123456789"), "0123456789"},
                new Object[] {DtUid.of("11223344556677889900"),
                        "11223344556677889900"}
        );
    }
}
