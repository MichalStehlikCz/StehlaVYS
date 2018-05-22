/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import com.provys.common.jsonb.JsonbHelper;
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
                    result = JsonbHelper.deserializeWithAdapters(
                            Class.forName(className).asSubclass(Dt.class), 
                            parser, context);
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
