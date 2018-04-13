/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter ensuring provys UID value is treated as simple string value when
 * serializing to / deserializing from Json using JSON-B
 * @author stehlik
 */
public class JsonbDtUidAdapter implements JsonbAdapter<DtUid, String> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     * @param original is source provys datatype value
     * @return unwraped value (String)
     */
    @Override
    public String adaptToJson(DtUid original) {
        return original.getValue();
    }

    /**
     * Wrap value from String to provys datatype when procesing JSON data
     * @param adapted is source value, read from JSON to String
     * @return wrapped provys datatype value
     */
    @Override
    public DtUid adaptFromJson(String adapted) {
        return new DtUid(adapted);
    }
}
