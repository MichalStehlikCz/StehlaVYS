/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

/**
 * Adapter ensuring provys optional name value is treated as simple string value
 * when serializing to / deserializing from Json using JSON-B.
 *
 * @author stehlik
 */
public class JsonbDtOptNameAdapter extends JsonbDtOptStringAdapter<DtOptName> {

    /**
     * Wrap value from String to provys datatype when procesing JSON data
     *
     * @param adapted is source value, read from JSON to String
     * @return wrapped provys datatype value
     */
    @Override
    public DtOptName adaptFromJson(String adapted) {
        return DtOptName.ofNullable(adapted);
    }

}
