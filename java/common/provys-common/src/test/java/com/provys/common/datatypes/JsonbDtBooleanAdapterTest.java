package com.provys.common.datatypes;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Test class for JsonbDtBooleanAdapter
 *
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtBooleanAdapterTest extends
        JsonbDtAdapterTest<DtBoolean, Boolean, JsonbDtBooleanAdapter> {

    /**
     * Initializes adapter used to run tests
     */
    @Before
    public void setUp() {
        adapter = new JsonbDtBooleanAdapter();
    }

    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[]{DtBoolean.of(true), true},
                 new Object[]{DtBoolean.of(false), false}
        );
    }

}
