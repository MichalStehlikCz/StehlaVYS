package com.provys.common.datatypes;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;

/**
 *
 * @author stehlik Test class for JsonbDtNameAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtNameAdapterTest extends
        JsonbDtAdapterTest<DtName, String, JsonbDtNameAdapter> {

    /**
     * Initializes adapter used to run tests
     */
    @Before
    public void setUp() {
        adapter = new JsonbDtNameAdapter();
    }

    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[]{new DtName("0123456789"), "0123456789"},
                 new Object[]{new DtName("abcd"), "abcd"}
        );
    }

}
