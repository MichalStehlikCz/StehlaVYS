/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import com.provys.common.jsonb.JsonbHelper;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.lang.reflect.Type;

/**
 * Deserialisation of Dt - finds proper subclass and deserialises using its
 * deserializer.
 *
 * @author stehlik
 */
public class JsonbDtDeserializer implements JsonbDeserializer<Dt> {

    @Override
    public Dt deserialize(JsonParser parser, DeserializationContext context,
            Type type) {
        Dt result = null;
        if (parser.hasNext()) {
            Event event = parser.next();
            if (event != Event.KEY_NAME) {
                throw new UnexpectedParserEventException(event, Event.KEY_NAME);
            }
            String className = parser.getString();
            parser.next();
            // all Dt subclasses have custom Jsonb adapter,
            // unfortunatelly it is not picked up by deserialize... so
            // we must apply it manually
            result = JsonbHelper.deserializeWithAdapters(
                    DtRepository.getClass(className),
                    parser, context);
            event = parser.next();
            if (event != JsonParser.Event.END_OBJECT) {
                throw new UnexpectedParserEventException(event,
                        Event.END_OBJECT);
            }
        }
        return result;
    }

    /**
     * Exception raised when other event is encountered than expected
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnexpectedParserEventException extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnexpectedParserEventException(JsonParser.Event event, JsonParser.Event expected) {
            super("Unexpected JSON parser event " + event + ", expected "
                    + expected);
        }
    }

}
