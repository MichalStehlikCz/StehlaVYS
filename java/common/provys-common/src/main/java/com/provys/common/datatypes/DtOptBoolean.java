/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.sql.Types;
import java.util.Objects;
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
public class DtOptBoolean extends DtOptional<Boolean> {

    private final static DtOptBoolean TRUE = new DtOptBoolean(true);
    private final static DtOptBoolean FALSE = new DtOptBoolean(false);
    
    /**
     * Retrieves DtOptBoolean value of true
     * 
     * @return returns static instance of DtOptBoolean representing true value
     */
    public static DtOptBoolean getTRUE() {
        return TRUE;
    }

    /**
     * Retrieves DtOptBoolean value of false
     * 
     * @return returns static instance of DtOptBoolean representing false value
     */
    public static DtOptBoolean getFALSE() {
        return FALSE;
    }

    private static final DtOptBoolean EMPTY = new DtOptBoolean();
    
    protected DtOptBoolean() {
        super();
    }

    /**
     * Returns an empty {@code Optional} instance.  No value is present for this
     * Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtOptBoolean}
     */
    public static DtOptBoolean empty() {
        return EMPTY;
    }

    /**
     * Constructs DtOptBoolean from PROVYS string value (Y/N)
     *
     * @param stringValue supplied string value
     * @return instance of DtBoolean, corresponding to value of supplied string;
     * returns null if supplied string is empty
     */
    public static DtOptBoolean ofStringValue(String stringValue) {
        if (stringValue == null) {
            return null;
        }
        switch (stringValue) {
            case "":
                return EMPTY;
            case "Y":
                return TRUE;
            case "N":
                return FALSE;
            default:
                throw new InvalidStringValue(stringValue);
        }
    }

    /**
     * Constructs DtOptBoolean from supplied non-null boolean value.
     *
     * @param value supplied boolean value
     * @return instance of DtBoolean with given value
     */
    public static DtOptBoolean of(Boolean value) {
        Objects.requireNonNull(value);
        return (value ? TRUE : FALSE);
    }

    /**
     * Constructs DtOptBoolean from supplied boolean value.
     * Value is optional - returns empty value if supplied value is null.
     *
     * @param value supplied boolean value
     * @return instance of DtBoolean with given value
     */
    public static DtOptBoolean ofNullable(Boolean value) {
        if (value == null) {
            return EMPTY;
        }
        return (value ? TRUE : FALSE);
    }

    /**
     * Constructs DtOptBoolean from supplied non-null DtBoolean value.
     *
     * @param value supplied DtBoolean value
     * @return instance of DtBoolean with given value
     */
    public static DtOptBoolean of(DtBoolean value) {
        Objects.requireNonNull(value);
        return (value.getValue() ? TRUE : FALSE);
    }

    /**
     * Constructs DtOptBoolean from supplied DtBoolean value.
     * Value is optional - returns empty value if supplied value is null.
     *
     * @param value supplied boolean value
     * @return instance of DtBoolean with given value
     */
    public static DtOptBoolean ofNullable(DtBoolean value) {
        if (value == null) {
            return EMPTY;
        }
        return (value.getValue() ? TRUE : FALSE);
    }

    /**
     * Constructs DtOptBoolean from supplied optional boolean value.
     * Returns static instance, thus it is more effective to use than
     * constructor
     *
     * @param value supplied boolean value
     * @return instance of DtBoolean with given value
     */
    public static DtOptBoolean of(Optional<Boolean> value) {
        Objects.requireNonNull(value);
        if (!value.isPresent()) {
            return EMPTY;
        }
        return (value.get() ? TRUE : FALSE);
    }

    /**
     * Constructs DtOptBoolean from supplied string value.
     * Returns static instance, thus it is more effective to use than
     * constructor.
     * 
     * @param value supplied string value; returns true if supplied value is
     * true (case insensitive) or 1, false if supplied value is false (case
     * insensitive) or 0, null if supplied value is null or empty String and
     * throws exception otherwise
     * @return instance of DtBoolean with given value
     */
    public static DtOptBoolean ofString(String value) {
        if ((value == null) || (value.isEmpty())) {
            return EMPTY;
        } else if (value.equalsIgnoreCase("true") || value.equals("1")) {
            return TRUE;
        } else if (value.equalsIgnoreCase("false") || value.equals("0")) {
            return FALSE;
        }
        throw new InvalidString(value);
    }

    /**
     * Register DtOptBoolean type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptBoolean.class, Types.CHAR
                , DtOptBoolean::validatePrecision);
    }
    
    static private final Optional<Integer> PRECISION = Optional.of(1);
    
    /**
     * Precision validator for {@code DtOptBoolean}.
     * 
     * @param precision is precision supplied on column creation
     * @return 1 as {@code DtOptBoolean} corresponds to CHAR(1 BYTE) column
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        return PRECISION;
    }

    /**
     * Default constructor - creates DtOptBoolean from supplied value
     *
     * @param value - supplied value new DtOptBoolean will be set to
     */
    private DtOptBoolean(boolean value) {
        super(Optional.of(value));
    }

    /**
     * Default constructor - creates DtOptBoolean from supplied value
     *
     * @param value - supplied value new DtOptBoolean will be set to
     */
    private DtOptBoolean(Optional<Boolean> value) {
        super(value);
    }

    @Override
    public String toStringValue() {
        if (!isPresent()) {
            return "";
        } else if (get()) {
            return "Y";
        } else {
            return "N";
        }
    }

    @Override
    public String toString() {
        if (!isPresent()) {
            return "";
        }
        return Boolean.toString(get());
    }

    @Override
    public String toSqlLiteral() {
        if (!isPresent()) {
            return "NULL";
        } else if (get()) {
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
        if (secondObject == null) {
            return false;
        }
        if (secondObject instanceof DtOptBoolean) {
            return getValue().equals(((DtOptBoolean) secondObject).getValue());
        } else if ((secondObject instanceof DtBoolean)
                && isPresent()) {
            return get() == ((DtBoolean) secondObject).getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (!isPresent()) {
            return 0;
        }
        return Boolean.hashCode(get());
    }

    /**
     * Exception raised when value supplied to fromStringValue is not valid
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InvalidStringValue extends ProvysException {

        private static final long serialVersionUID = 1L;

        InvalidStringValue(String stringValue) {
            super("Invalid provys string value for DtBoolean: " + stringValue);
        }
    }

    /**
     * Exception raised when value supplied to fromString is not valid
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InvalidString extends ProvysException {

        private static final long serialVersionUID = 1L;

        InvalidString(String stringValue) {
            super("Invalid string as source for DtBoolean: " + stringValue);
        }
    }

}
