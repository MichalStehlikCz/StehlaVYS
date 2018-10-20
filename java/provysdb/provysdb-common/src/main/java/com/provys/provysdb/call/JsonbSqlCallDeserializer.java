/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.error.ProvysException;
import com.provys.common.jsonb.JsonbHelper;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 * Custom JSON-B deserializer for SqlCall.
 * There are problems with arrays of objects with subtypes, as array
 * deserializer does not invoke annotation-based adapters for items
 * 
 * @author stehlik
 */
public class JsonbSqlCallDeserializer  implements JsonbDeserializer<SqlCall> {

    @Override
    public SqlCall deserialize(JsonParser parser,
             DeserializationContext context, Type type) {
        String sql = null;
        final List<BindValue> values = new ArrayList<>(1);
        List<ColumnDef> columns = new ArrayList<>(1);
        while (parser.hasNext()) {
            Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    switch (parser.getString()) {
                        case "sql":
                            parser.next();
                            sql = context.deserialize(String.class, parser);
                            break;
                        case "values":
                            event = parser.next();
                            if (event != Event.START_ARRAY) {
                                throw new UnexpectedParserEventException(event,
                                         Event.START_ARRAY);
                            }
                            event = parser.next();
                            while (event != Event.END_ARRAY) {
                                if (event != Event.START_OBJECT) {
                                    throw new UnexpectedParserEventException(
                                            event, Event.START_OBJECT);
                                }
                                values.add(JsonbHelper.deserializeWithAdapters(
                                        BindValue.class, parser, context));
                                event = parser.next();
                            }
                            break;
                        case "columns":
                            event = parser.next();
                            if (event != Event.START_ARRAY) {
                                throw new UnexpectedParserEventException(event,
                                         Event.START_ARRAY);
                            }
                            event = parser.next();
                            while (event != Event.END_ARRAY) {
                                if (event != Event.START_OBJECT) {
                                    throw new UnexpectedParserEventException(
                                            event, Event.START_OBJECT);
                                }
                                columns.add(JsonbHelper.deserializeWithAdapters(
                                        ColumnDef.class, parser, context));
                            }
                            break;
                    }
                    break;
                case END_OBJECT:
                    return new SqlCall(sql, values, columns);
                default:
                    throw new UnexpectedParserEventException(event
                            , Event.KEY_NAME);
            }
        }
        throw new UnexpectedEndOfJsonException();
    }

    /**
     * Exception raised when other event is encountered than expected
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnexpectedParserEventException extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnexpectedParserEventException(Event event, Event expected) {
            super("Unexpected JSON parser event " + event + ", expected "
                    + expected);
        }
    }

    /**
     * Exception raised when end of file is encountered before end of object
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnexpectedEndOfJsonException extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnexpectedEndOfJsonException() {
            super("Parser has unexpectedly run out of events");
        }
    }

}
