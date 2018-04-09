/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

/**
 *
 * @author stehlik
 */
public class JsonbDtNameNmDeserializer implements JsonbDeserializer<DtNameNm> {
    @Override
    public DtNameNm deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        DtNameNm dt = null;
        if (!jsonParser.hasNext()){
            throw new RuntimeException("Dt Json deserializer: nothing found");
        }
        JsonParser.Event event = jsonParser.next();
        if (event == JsonParser.Event.VALUE_STRING || event==JsonParser.Event.VALUE_NUMBER) {
            dt=new DtNameNm(jsonParser.getString());
        } else {
            throw new RuntimeException("Dt Json deserializer: value expected, "+event.toString()+" found");
        }
        return dt;
    }
}