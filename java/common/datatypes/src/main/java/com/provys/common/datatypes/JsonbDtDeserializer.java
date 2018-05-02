/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.lang.reflect.Type;
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
        String subclass = null;
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            if (event == JsonParser.Event.KEY_NAME) {
                String keyName = parser.getString();
                if (keyName.equals("type")) {
                    subclass = context.deserialize(String.class, parser);
                } else if (keyName.equals("value")) {
                    if (subclass == null) {
                        throw new TypeNotSpecifiedException();
                    }
                    try {
                        return (Dt) context.deserialize(
                                Class.forName(subclass), parser);
                    } catch (ClassNotFoundException e) {
                        throw new DtClassNotFoundException(subclass, e);
                    }
                }
            }
            parser.next();
        }
        throw new ValueNotFoundException();
    }


    /**
     * Exception raised when subclass is not known and value is found
     */
    @SuppressWarnings("PublicInnerClass")
    static public class TypeNotSpecifiedException extends ProvysException {

        private static final long serialVersionUID = 1L;

        TypeNotSpecifiedException() {
            super("Type not found before value in Json deserialisation of Dt");
        }
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

    /**
     * Exception raised when value not found by deserialiser
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ValueNotFoundException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ValueNotFoundException() {
            super("Value not found during deserialisation of Dt");
        }
    }
}
