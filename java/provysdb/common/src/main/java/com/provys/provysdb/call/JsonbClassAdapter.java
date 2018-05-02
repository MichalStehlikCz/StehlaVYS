/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author stehlik
 */
public class JsonbClassAdapter implements JsonbAdapter<Class<?>, JsonValue> {

    @Override
    public JsonValue adaptToJson(Class<?> original) throws Exception {
        return Json.createValue(original.getName());
    }

    @Override
    public Class<?> adaptFromJson(JsonValue adapted) throws Exception {
        return Class.forName(adapted.toString());
    }
    
}
