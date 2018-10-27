package com.provys.common.datatypes;

import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Unit test class for JsonbDtNameAdapter
 *
 * @author stehlik
 */
@RunWith(JUnitParamsRunner.class)
public class JsonbDtNameNmAdapterTest extends
        JsonbDtAdapterTest<DtNameNm, String, JsonbDtNameNmAdapter> {

    /**
     * Initializes adapter used to run tests
     */
    @Before
    public void setUp() {
        adapter = new JsonbDtNameNmAdapter();
    }

    private List<Object[]> parametersForAdapter() {
        return asList(
                new Object[]{DtNameNm.of("A0123456789"), "A0123456789"},
                new Object[]{DtNameNm.of("ABCD"), "ABCD"},
                new Object[]{DtNameNm.of("-ABCD"), "-ABCD"}
        );
    }

}
