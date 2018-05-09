/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

/**
 * Deserialisation of Dt - finds proper subclass and deserialises using its
 * deserializer.
 * @author stehlik
 */
public class JsonbDtDeserializer implements JsonbDeserializer<Dt> {

    @Override
    public Dt deserialize(JsonParser parser, DeserializationContext context,
            Type type) {
        Dt result = null;
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            if (event == JsonParser.Event.KEY_NAME) {
                String className = parser.getString();
                parser.next();
                try {
                    // all Dt subclasses have custom Jsonb adapter,
                    // unfortunatelly it is not picked up by deserialize... so
                    // we must apply it manually
                    JsonbTypeAdapter adapterType = Class.forName(className).
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
                            result = (Dt) adaptFromJson.invoke(adapter, adapted);
                        } catch (IllegalAccessException ex) {
                            throw new ProvysException("Cannot access method adaptToJson in adapter", ex);
                        } catch (IllegalArgumentException ex) {
                            throw new ProvysException("Illegal argument to method adaptToJson in adapter", ex);
                        } catch (InvocationTargetException ex) {
                            throw new ProvysException("Invalid target when calling method adaptToJson", ex);
                        }
                    } else {
                        result = context.deserialize(Class.forName(className).
                                asSubclass(Dt.class), parser);
                    }
                } catch (ClassNotFoundException e) {
                    throw new DtClassNotFoundException(className, e);
                }
            }
            parser.next();
        }
        return result;
    }

    /**
     * Exception raised when class supplied to deserialiser does not exist
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DtClassNotFoundException extends ProvysException {

        private static final long serialVersionUID = 1L;

        DtClassNotFoundException(String className, Throwable cause) {
            super("Class not found during Jsonb deserialisation: "+className,
                    cause);
        }
    }

}
