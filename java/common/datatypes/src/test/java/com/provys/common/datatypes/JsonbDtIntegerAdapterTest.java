package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author stehlik Test class for JsonbDtIntegerAdapter
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtIntegerAdapterTest extends
        JsonbDtAdapterTest<DtInteger, Integer, JsonbDtIntegerAdapter> {

    /**
     * Initializes adapter used to run tests
     */
    @Before
    public void setUp() {
        adapter = new JsonbDtIntegerAdapter();
    }

    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[]{new DtInteger(12345), 12345},
                new Object[]{new DtInteger(-12345), -12345}
        );
    }
}
