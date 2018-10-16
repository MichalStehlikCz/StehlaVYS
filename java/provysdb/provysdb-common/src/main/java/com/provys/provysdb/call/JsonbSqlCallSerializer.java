/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.jsonb.JsonbHelper;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

/**
 * JSON-B custom serializer for sqlCall type.
 * Current yasson version does not properly do lookup of custom adapters during
 * array serialization / deserialization... thus we have to do it ourselves.
 * 
 * @author stehlik
 */
public class JsonbSqlCallSerializer implements JsonbSerializer<SqlCall> {

    @Override
    public void serialize(SqlCall object, JsonGenerator generator,
            SerializationContext ctx) {
        if (object != null) {
            generator.writeStartObject();
            generator.write("sql", object.getSql());
            if (!object.getValues().isEmpty()) {
                generator.writeStartArray("values");
                object.getValues().forEach((value) -> {
                    JsonbHelper.serializeWithAdapters(value, generator, ctx);
                });
                generator.writeEnd();
            }
            if (!object.getColumns().isEmpty()) {
                generator.writeStartArray("columns");
                object.getColumns().forEach((column) -> {
                    JsonbHelper.serializeWithAdapters(column, generator, ctx);
                });
                generator.writeEnd();
            }
        } else {
            ctx.serialize(null, generator);
        }
    }

}