
package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Test class for JsonbDtBooleanAdapter
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
                new Object[] {DtBoolean.fromValue(true), true}
                , new Object[] {DtBoolean.fromValue(false), false}
        );
    }

}
