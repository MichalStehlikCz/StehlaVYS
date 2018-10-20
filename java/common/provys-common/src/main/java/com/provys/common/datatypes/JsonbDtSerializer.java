/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.jsonb.JsonbHelper;
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
        if (object != null) {
            generator.writeStartObject();
            generator.writeKey(object.getClass().getSimpleName());
            // all Dt subclasses have custom Jsonb adapter, unfortunatelly it is
            // not picked up by serialize... so we must apply it ourselves
            JsonbHelper.serializeWithAdapters(object, generator, ctx);
            generator.writeEnd();
        } else {
            ctx.serialize(null, generator);
        }
    }

}
