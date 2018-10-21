/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.sql.Types;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Represents values of PROVYS domain BOOLEAN.
 * Value is represented as CHAR(1 BYTE) in Oracle tables, with TRUE/FALSE
 * values represented by 'Y'/'N'. In Java, value is stored as boolean TRUE
 * or FALSE value.
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtBooleanAdapter.class)
public class DtBoolean implements Dt {

    private final static DtBoolean TRUE = new DtBoolean(true);
    private final static DtBoolean FALSE = new DtBoolean(false);
    
    /**
     * Retrieves DtBoolean value of true
     * 
     * @return returns static instance of DtBoolean representing true value
     */
    public static DtBoolean getTRUE() {
        return TRUE;
    }

    /**
     * Retrieves DtBoolean value of false
     * 
     * @return returns static instance of DtBoolean representing false value
     */
    public static DtBoolean getFALSE() {
        return FALSE;
    }

    /**
     * Constructs DtBoolean from PROVYS string value (Y/N)
     *
     * @param stringValue supplied string value
     * @return instance of DtBoolean, corresponding to value of supplied string;
     * returns null if supplied string is empty
     */
    public static DtBoolean fromStringValue(String stringValue) {
        if (stringValue == null) {
            throw new NullStringValueException();
        }
        switch (stringValue) {
            case "Y":
                return TRUE;
            case "N":
                return FALSE;
            default:
                throw new InvalidStringValueException(stringValue);
        }
    }

    /**
     * Constructs DtBoolean from supplied boolean value.
     * Returns static instance, thus it is morfe effective to use than
     * constructor
     *
     * @param value supplied boolean value
     * @return instance of DtBoolean with given value
     */
    public static DtBoolean fromValue(boolean value) {
        return (value ? TRUE : FALSE);
    }

    /**
     * Constructs DtBoolean from supplied string value.
     * Returns static instance, thus it is more effective to use than
     * constructor.
     * 
     * @param value supplied string value; returns true if supplied value is
     * true (case insensitive) or 1, false if supplied value is false (case
     * insensitive) or 0, null if supplied value is null and throws exception
     * otherwise
     * @return instance of DtBoolean with given value
     */
    public static DtBoolean fromString(String value) {
        if (value == null) {
            throw new NullStringException();
        }
        if (value.equalsIgnoreCase("true") || value.equals("1")) {
            return TRUE;
        } else if (value.equalsIgnoreCase("false") || value.equals("0")) {
            return FALSE;
        }
        throw new InvalidStringException(value);
    }

    /**
     * Register DtBoolean type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtBoolean.class, Types.CHAR
                , DtBoolean::validatePrecision);
    }
    
    static private final Optional<Integer> PRECISION = Optional.of(1);
    
    /**
     * Precision validator for {@code DtBoolean}.
     * 
     * @param precision is precision supplied on column creation
     * @return 1 as {@code DtBoolean} corresponds to CHAR(1 BYTE) column
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        return PRECISION;
    }
    
    private final boolean value;

    /**
     * Default constructor - creates DtBoolean from supplied value
     *
     * @param value - supplied value new DtBoolean will be set to
     */
    private DtBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Value retrieval
     *
     * @return returns boolean value, corresponding to this object's effective
     * value
     */
    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toStringValue() {
        if (this.value) {
            return "Y";
        } else {
            return "N";
        }
    }

    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }

    @Override
    public String toSqlLiteral() {
        if (this.value) {
            return "'Y'";
        } else {
            return "'N'";
        }
    }
    
    @Override
    public boolean equals(Object secondObject) {
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtBoolean) {
            return getValue() == ((DtBoolean) secondObject).getValue();
        } else if (secondObject instanceof DtOptBoolean) {
            if (!((DtOptBoolean) secondObject).isPresent()) {
                return false;
            }
            return ((DtOptBoolean) secondObject).get() == getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(this.value);
    }

    /**
     * Exception raised when value supplied to fromStringValue is not valid
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InvalidStringValueException extends ProvysException {

        private static final long serialVersionUID = 1L;

        InvalidStringValueException(String stringValue) {
            super("Invalid provys string value for DtBoolean: " + stringValue);
        }
    }

    /**
     * Exception raised when value supplied to fromStringValue is null
     */
    @SuppressWarnings("PublicInnerClass")
    static public class NullStringValueException extends ProvysException {

        private static final long serialVersionUID = 1L;

        NullStringValueException() {
            super("Null provys string value for DtBoolean");
        }
    }

    /**
     * Exception raised when value supplied to fromString is not valid
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InvalidStringException extends ProvysException {

        private static final long serialVersionUID = 1L;

        InvalidStringException(String stringValue) {
            super("Invalid string as source for DtBoolean: " + stringValue);
        }
    }

    /**
     * Exception raised when value supplied to fromString is not null
     */
    @SuppressWarnings("PublicInnerClass")
    static public class NullStringException extends ProvysException {

        private static final long serialVersionUID = 1L;

        NullStringException() {
            super("Invalid string as source for DtBoolean");
        }
    }

}
