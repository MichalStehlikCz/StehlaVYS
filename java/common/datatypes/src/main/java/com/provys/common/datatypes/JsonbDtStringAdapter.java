/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter ensuring provys string value is treated as simple string value when
 * serializing to / deserializing from Json using JSON-B
 * @author stehlik
 * @param <T> subclass of DtString adapter is used for
 */
abstract public class JsonbDtStringAdapter<T extends DtString>
        implements JsonbAdapter<T, String> {

    /**
     * Unwrap value to ensure it is properly serialized to Json
     * @param original is source provys datatype value
     * @return unwraped value (string)
     */
    @Override
    public String adaptToJson(T original) {
        return original.getValue();
    }

}
