/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author stehlik
 */
public class JsonbDtSerializer implements JsonbSerializer<Dt> {
    
    @Override
    public void serialize(Dt object, JsonGenerator generator,
            SerializationContext ctx) {
        generator.writeStartObject();
        generator.write("type", object.getClass().getSimpleName());
        ctx.serialize("value", object, generator);
        generator.writeEnd();
    }
    
}
