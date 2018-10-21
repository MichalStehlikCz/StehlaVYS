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
 * Representation of optional PROVYS UID values.
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptUidAdapter.class)
public class DtOptUid extends DtOptional<String> {

    /**
     * Register {@code DtUid} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptUid.class, Types.VARCHAR
                , DtOptUid::validatePrecision, DtOptUid::validateScale
                , DtOptUid::eligibleForSqlType);
    }
    
    static private final Optional<Integer> PRECISION = Optional.of(38);

    /**
     * Precision validator for {@code DtOptUid}.
     * 
     * @param precision is precision supplied on column creation
     * @return 38 as it is fixed precision of PROVYS Uid
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        return PRECISION;
    }
        
    static private final Optional<Short> SCALE = Optional.of((short) 0);
    
    /**
     * Scale validator for {@code DtUid}.
     * 
     * @param scale is scale supplied on column creation
     * @return 0 as UID is whole number
     */
    static public Optional<Short> validateScale(
            Optional<Short> scale) {
        return SCALE;
    }
        
    /**
     * Marks {@code DtOptUid} as default for Numeric columns if column name ends
     * with _ID or _RF.
     * 
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 30 if {@code DtUid} should be used for column and -1 otherwise
    */
    public static int eligibleForSqlType(int sqlType
            , Optional<Integer> precision, Optional<Short> scale
            , boolean isNullable, String name) {
        if ((sqlType == Types.NUMERIC)
                && ((name.endsWith("_ID"))
                || (name.endsWith("_RF")))) {
            return 29;
        }
        return -1;
    }

    private static final DtOptUid EMPTY = new DtOptUid();
    
    protected DtOptUid() {
        super();
    }

    /**
     * Returns an empty {@code DtOptUid} instance.  No value is present for this
     * Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtOptUid}
     */
    public static DtOptUid empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptUid} with the specified present non-null value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptUid} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptUid of(String value) {
        Objects.requireNonNull(value);
        return new DtOptUid(value);
    }

    /**
     * Returns an {@code DtOptUid} describing the specified value, if
     * non-null, otherwise returns an empty {@code DtOptUid}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptUid} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptUid}
     */
    public static DtOptUid ofNullable(String value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Creates provys UID value from supplied string
     * @param value represents value this object will be assigned
     */
    private DtOptUid(String value) {
        super(value);
        if (value.length()>38) {
            throw new UidTooLongException(value);
        }
        if (!value.matches("\\d+")) {
            throw new UidNotNumberException(value);
        }
    }
    
    @Override
    public String toStringValue(){
        return getValue().orElse("");
    }

    @Override
    public String toString(){
        return getValue().orElse("");
    }
    
    @Override
    public String toSqlLiteral() {
        return getValue().orElse("TO_NUMBER(NULL)");
    }
    
    @Override
    public boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtOptUid) {
            return getValue().equals(((DtOptUid) secondObject).getValue());
        }
        if ((isPresent()) && (secondObject instanceof DtUid)) {
            return get().equals(((DtUid) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return map(val -> val.hashCode()).orElse(0);
    }
    /**
     * Exception indicating that supplied value is not valid PROVYS Uid, because
     * it cannot be longer than 38 digits
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UidTooLongException extends ProvysException {

        private static final long serialVersionUID = 1L;
        /**
         * Creates exception and embeds supplied value to error message
         * @param value is offending value that is too long
         */
        public UidTooLongException(String value) {
            super("Value supplied to Uid constructor is too long ("+value+")");
        }
    }
    /**
     * Exception indicating that supplied value is not valid PROVYS Uid, because
     * it contains non/numeric characters
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UidNotNumberException extends ProvysException {

        private static final long serialVersionUID = 1L;
        /**
         * Creates exception and embeds supplied value to error message
         * @param value is offending value that is non/numeric
         */
        public UidNotNumberException(String value) {
            super("Value supplied to Uid constructor is not a number ("+value+")");
        }
    }
}
