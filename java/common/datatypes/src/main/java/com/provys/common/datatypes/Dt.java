/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;

/**
 * Abstract ancestor of PROVYS datatype classes. All values in PROVYS objects
 * are kept in datatype classes, primitive types are not used. This enables
 * seemless translation to database types, formatting etc. PROVYS datatypes
 * support null value by using null pointer; if object exists, it always
 * contains non-null value. Values are final, change is achieved by assigning
 * new instance of datatype class
 *
 * All subclasses have to implement value based comparison and hashing. They
 * also define customized Json serializer / deserializer, as simple types are
 * serialized as value, not as object
 *
 * @author stehlik
 */
@JsonbTypeSerializer(JsonbDtSerializer.class)
@JsonbTypeDeserializer(JsonbDtDeserializer.class)
abstract public class Dt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Translation of value in given PROVYS datatype to String, synchronised
     * with domain to string translation in server
     *
     * @return value of given value object translated to String, corresponding
     * to translation to VARCHAR2 performed on server
     */
    abstract public String toStringValue();

    @Override
    abstract public boolean equals(Object second);

    @Override
    abstract public int hashCode();

    /**
     * Exception raised on attempt to create new instance of one of PROVYS
     * datatype classes with null value
     */
    static public class NullValueNotSupportedException extends ProvysException {

        private static final long serialVersionUID = 1L;

        /**
         * Creates NullValueNotSupportedException using hardcoded error message
         */
        public NullValueNotSupportedException() {
            super("Null value not supported in Provys value objects");
        }
    }

}
