/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;
import java.math.BigDecimal;

/**
 * Adapter ensuring provys number value is treated as numeric value when
 * serializing to / deserializing from Json using JSON-B
 *
 * @author stehlik
 */
public class JsonbDtNumberAdapter implements JsonbAdapter<DtNumber, BigDecimal> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     *
     * @param original is source provys datatype value
     * @return unwraped value (BigDecimal)
     */
    @Override
    public BigDecimal adaptToJson(DtNumber original) {
        return original.getValue();
    }

    /**
     * Wrap value from BigDecimal to provys datatype when procesing JSON data
     *
     * @param adapted is source value, read from JSON to BigDecimal
     * @return wrapped provys datatype value
     */
    @Override
    public DtNumber adaptFromJson(BigDecimal adapted) {
        return DtNumber.of(adapted);
    }

}
