package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.JsonbDtSerializer;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.stream.JsonParser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Adapter for serialisation and deserialisation of BindValue using JSON-B.
 * It serves two purposes - first, JSON-B does not support class hierarchy and
 * thus would not properly deserialize BindParameter, second we do not send type
 * through JSON-B as it can be taken from value
 * 
 * @author stehlik
 */
public class JsonbBindValueAdapter
        implements JsonbAdapter<BindValue, JsonObject> {
    
    @Override
    public JsonObject adaptToJson(BindValue value) throws Exception {
        if (value == null) {
            return null;
        }
        if (value instanceof BindParameter) {
            return new JsonbBindParameterAdapter()
                    .adaptToJson((BindParameter) value);
        }
        String jsonValue = JsonbBuilder.create(
                        new JsonbConfig().withSerializers(
                                new JsonbDtSerializer()))
                .toJson(value.getValue(), Dt.class);
        return Json.createObjectBuilder()
                .add("name", value.getName())
                .add("value", Json.createObjectBuilder(Json.createReader(
                        new StringReader(jsonValue)).readObject())).build();
    }

    @Override
    public BindValue adaptFromJson(JsonObject jsonObject) throws Exception {
        if (jsonObject.containsKey("mode")) {
            return new JsonbBindParameterAdapter().adaptFromJson(jsonObject);
        }
        return new BindValue(jsonObject.getString("name"), JsonbBuilder.create()
                .fromJson(jsonObject.get("value").toString(), Dt.class));
    }
    
}
