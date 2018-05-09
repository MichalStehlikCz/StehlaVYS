/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbTypeAdapter;
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
            // all Dt subclasses have custom Jsonb adapter, unfortunatelly it is
            // not picked up by serialize... so we must apply it manually
            JsonbTypeAdapter adapterType = object.getClass().getAnnotation(
                    JsonbTypeAdapter.class);
            if (adapterType != null) {
                JsonbAdapter<?, ?> adapter;
                try {
                    adapter = adapterType.value().newInstance();
                } catch (InstantiationException ex) {
                    throw new ProvysException("Cannot instantiate adapter", ex);
                } catch (IllegalAccessException ex) {
                    throw new ProvysException("Illegal access during adapter instantiation", ex);
                }
                Method adaptToJson;
                try {
                    adaptToJson = adapter.getClass().getMethod("adaptToJson",
                            object.getClass());
                } catch (NoSuchMethodException ex) {
                    throw new ProvysException("Method adaptToJson not found in adapter", ex);
                } catch (SecurityException ex) {
                    throw new ProvysException("Cannot access method adaptToJson in adapter", ex);
                }
                Object adapted;
                try {
                    adapted = adaptToJson.invoke(adapter, object);
                } catch (IllegalAccessException ex) {
                    throw new ProvysException("Cannot access method adaptToJson in adapter", ex);
                } catch (IllegalArgumentException ex) {
                    throw new ProvysException("Illegal argument to method adaptToJson in adapter", ex);
                } catch (InvocationTargetException ex) {
                    throw new ProvysException("Invalid target when calling method adaptToJson", ex);
                }
                ctx.serialize(object.getClass().getName(), adapted, generator);
            } else {
                ctx.serialize(object.getClass().getName(), object, generator);
            }
            generator.writeEnd();
        } else {
            ctx.serialize(null, generator);
        }
    }

}
