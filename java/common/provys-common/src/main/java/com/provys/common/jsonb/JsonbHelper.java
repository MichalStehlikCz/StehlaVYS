/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.jsonb;

import com.provys.common.error.ProvysException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

/**
 *
 * @author stehlik
 */
public class JsonbHelper {

    /**
     * Method invokes JSON-B adapters defined in annotations and serializes
     * adapted object.
     *
     * Hopefully this functionality eventually becomes part of JSON-B standard
     * as an option when invoking serialisation. As of now, calling
     * SerialisationContext's serialize method ignores custom adapters. To fix
     * it, this method allows to find adapter and adapt object before invoking
     * serialize. If no adapters are found, it serializes supplied object
     *
     * @param object - source object (to be adapted using JSON-B adapters)
     * @param generator - Json generator used for serialization
     * @param ctx - serialization context used for serialization
     */
    public static void serializeWithAdapters(Object object,
            JsonGenerator generator, SerializationContext ctx) {
        Object adapted;
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
            Method[] methods = adapter.getClass().getMethods();
            Method adaptToJson = null;
            for (Method method : methods) {
                if (method.getName().equals("adaptToJson")) {
                    adaptToJson = method;
                    break;
                }
            }
            if (adaptToJson == null) {
                throw new ProvysException("Haven't found adaptFromJson method in adapter");
            }
            try {
                adapted = adaptToJson.invoke(adapter, object);
            } catch (IllegalAccessException ex) {
                throw new ProvysException("Cannot access method adaptToJson in adapter", ex);
            } catch (IllegalArgumentException ex) {
                throw new ProvysException("Illegal argument to method adaptToJson in adapter", ex);
            } catch (InvocationTargetException ex) {
                throw new ProvysException("Invalid target when calling method adaptToJson", ex);
            }
        } else {
            adapted = object;
        }
        ctx.serialize(adapted, generator);
    }

    /**
     * Method finds JSON-B adapter defined in annotations and deserializes
     * adapted object, then adapts it to source object.
     *
     * Hopefully this functionality eventually becomes part of JSON-B standard
     * as an option when invoking deserialisation. As of now, calling
     * SerialisationContext's deserialize method ignores custom adapters. To fix
     * it, this method allows to find adapter and deserialize adapted type, then
     * invoke adapter to retrieve source object
     *
     * @param <T> represents common ancestor of objects to be deserialized
     * @param targetClass - class of result object
     * @param parser - parser containing source JSON text
     * @param context - context used for actual deserialization
     * @return returns object, deserialized from supplied parser
     */
    public static <T> T deserializeWithAdapters(Class<? extends T> targetClass,
            JsonParser parser, DeserializationContext context) {
        T result;

        JsonbTypeAdapter adapterType = targetClass.
                getAnnotation(JsonbTypeAdapter.class);
        if (adapterType != null) {
            JsonbAdapter<?, ?> adapter;
            try {
                adapter = adapterType.value().newInstance();
            } catch (InstantiationException ex) {
                throw new ProvysException("Cannot instantiate adapter", ex);
            } catch (IllegalAccessException ex) {
                throw new ProvysException("Illegal access during adapter instantiation", ex);
            }
            Method[] methods = adapter.getClass().getMethods();
            Method adaptFromJson = null;
            for (Method method : methods) {
                if (method.getName().equals("adaptFromJson")) {
                    adaptFromJson = method;
                    break;
                }
            }
            if (adaptFromJson == null) {
                throw new ProvysException("Haven't found adaptFromJson method in adapter");
            }
            Class<?> objectType = adaptFromJson.getParameterTypes()[0];
            Object adapted = context.deserialize(objectType, parser);
            try {
                result = (T) adaptFromJson.invoke(adapter, adapted);
            } catch (IllegalAccessException ex) {
                throw new ProvysException("Cannot access method adaptToJson in adapter", ex);
            } catch (IllegalArgumentException ex) {
                throw new ProvysException("Illegal argument to method adaptToJson in adapter", ex);
            } catch (InvocationTargetException ex) {
                throw new ProvysException("Invalid target when calling method adaptToJson", ex);
            }
        } else {
            result = context.deserialize(targetClass, parser);
        }
        return result;
    }
}