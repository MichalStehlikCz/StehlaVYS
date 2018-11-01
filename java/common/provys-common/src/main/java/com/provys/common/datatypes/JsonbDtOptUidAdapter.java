/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter ensuring optional provys UID value is treated as simple string value
 * when serializing to / deserializing from Json using JSON-B
 *
 * @author stehlik
 */
public class JsonbDtOptUidAdapter implements JsonbAdapter<DtOptUid, String> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     *
     * @param original is source provys datatype value
     * @return unwraped value (String)
     */
    @Override
    public String adaptToJson(DtOptUid original) {
        return original.getValue().orElse(null);
    }

    /**
     * Wrap value from String to provys datatype when procesing JSON data
     *
     * @param adapted is source value, read from JSON to String
     * @return wrapped provys datatype value
     */
    @Override
    public DtOptUid adaptFromJson(String adapted) {
        return DtOptUid.ofNullable(adapted);
    }
}
