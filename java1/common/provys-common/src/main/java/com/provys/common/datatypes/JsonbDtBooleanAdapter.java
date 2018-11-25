/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter ensuring provys boolean value is treated as boolean value when
 * serializing to / deserializing from Json using JSON-B
 *
 * @author stehlik
 */
public class JsonbDtBooleanAdapter implements JsonbAdapter<DtBoolean, Boolean> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     *
     * @param original is source provys datatype value
     * @return unwraped value (primitive type wrapper)
     */
    @Override
    public Boolean adaptToJson(DtBoolean original) {
        return original.getValue();
    }

    /**
     * Wrap value from primitive type wrapper to provys datatype when procesing
     * JSON data
     *
     * @param adapted is source value, read from JSON to primitive type wrapper
     * @return wrapped provys datatype value
     */
    @Override
    public DtBoolean adaptFromJson(Boolean adapted) {
        return DtBoolean.of(adapted);
    }

}
