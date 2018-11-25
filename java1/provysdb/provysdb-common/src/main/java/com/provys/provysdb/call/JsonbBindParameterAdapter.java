/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Adapter for serialisation and deserialisation of BindValue using JSON-B.
 * It serves two purposes - first, JSON-B does not support class hierarchy and
 * thus would not properly deserialize BindParameter, second we do not send type
 * through JSON-B as it can be taken from value
 * 
 * @author stehlik
 */
public class JsonbBindParameterAdapter
        implements JsonbAdapter<BindParameter, JsonObject>  {
    
    @Override
    public JsonObject adaptToJson(BindParameter value) throws Exception {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
                .add("name", value.getName())
                .add("value", JsonbBuilder.create().toJson(value.getValue()))
                .add("mode", value.getMode().toString());
        return jsonBuilder.build();
    }

    @Override
    public BindParameter adaptFromJson(JsonObject jsonObject) throws Exception {
        return new BindParameter(jsonObject.getString("name")
                , JsonbBuilder.create().fromJson(jsonObject.get("value")
                        .toString(), Dt.class)
                , ParameterMode.valueOf(jsonObject.getString("mode", "IN")));
    }
    
}
