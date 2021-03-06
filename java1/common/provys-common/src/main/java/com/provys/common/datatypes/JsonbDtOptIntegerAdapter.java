/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter ensuring provys optional integer value is treated as plain numeric
 * value when serializing to / deserializing from Json using JSON-B.
 *
 * @author stehlik
 */
public class JsonbDtOptIntegerAdapter
        implements JsonbAdapter<DtOptInteger, Integer> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     *
     * @param original is source provys datatype value
     * @return unwraped value (Integer)
     */
    @Override
    public Integer adaptToJson(DtOptInteger original) {
        return original.orElse(null);
    }

    /**
     * Wrap value from Integer to provys datatype when procesing JSON data
     *
     * @param adapted is source value, read from JSON to Integer
     * @return wrapped provys datatype value
     */
    @Override
    public DtOptInteger adaptFromJson(Integer adapted) {
        return DtOptInteger.ofNullable(adapted);
    }

}
