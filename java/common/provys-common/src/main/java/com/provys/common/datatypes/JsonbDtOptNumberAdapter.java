/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;
import java.math.BigDecimal;

/**
 * Adapter ensuring provys optional number value is treated as plain numeric
 * value when serializing to / deserializing from Json using JSON-B.
 *
 * @author stehlik
 */
public class JsonbDtOptNumberAdapter
        implements JsonbAdapter<DtOptNumber, BigDecimal> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     *
     * @param original is source provys datatype value
     * @return unwraped value (Integer)
     */
    @Override
    public BigDecimal adaptToJson(DtOptNumber original) {
        return original.orElse(null);
    }

    /**
     * Wrap value from BigDecimal to provys datatype when procesing JSON data
     *
     * @param adapted is source value, read from JSON to BigDecimal
     * @return wrapped provys datatype value
     */
    @Override
    public DtOptNumber adaptFromJson(BigDecimal adapted) {
        return DtOptNumber.ofNullable(adapted);
    }

}
