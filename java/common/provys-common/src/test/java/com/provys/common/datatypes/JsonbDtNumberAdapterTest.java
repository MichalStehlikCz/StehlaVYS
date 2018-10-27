
package com.provys.common.datatypes;

import java.math.BigDecimal;
import static java.util.Arrays.asList;
import java.util.List;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
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
     * Initializes adapter used to run tests
     */
    @Before
    public void setUp() {
        adapter = new JsonbDtNumberAdapter();
    }
    
    private List<Object[]> parametersForAdapter() {
        return asList(new Object[] {DtNumber.of(new BigDecimal(123456789))
                        , new BigDecimal(123456789)}
                , new Object[] {DtNumber.of(
                        BigDecimal.valueOf(123456789.01234567)),
                        BigDecimal.valueOf(123456789.01234567)}
        );
    }

}
