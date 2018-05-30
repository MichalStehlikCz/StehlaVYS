/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter ensuring ROWID value is treated as simple string value when
 * serializing to / deserializing from Json using JSON-B
 * @author stehlik
 */
public class JsonbDtRowIdAdapter implements JsonbAdapter<DtRowId, String> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     * @param original is source RowId datatype value
     * @return unwrapped value (String)
     */
    @Override
    public String adaptToJson(DtRowId original) {
        return original.toStringValue();
    }

    /**
     * Wrap value from String to RowId datatype when processing JSON data
     * @param adapted is source value, read from JSON to String
     * @return wrapped RowId datatype value
     */
    @Override
    public DtRowId adaptFromJson(String adapted) {
        return new DtRowId(adapted);
    }
}
