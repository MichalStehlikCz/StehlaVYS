/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Objects;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Used to store optional INTEGER values (usually NUMBER(5, 0) in Oracle).
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptNumberAdapter.class)
public class DtOptNumber extends DtOptional<BigDecimal>
        implements DtOptNumeric {

    /**
     * Register DtOptNumber type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptNumber.class, Types.INTEGER
                , DtOptNumber::validatePrecision, DtOptNumber::validateScale
                , DtOptNumber::eligibleForSqlType);
    }
    
    /**
     * Precision validator for {@code DtNumber}.
     * 
     * @param precision is precision supplied on column creation
     * @return specified precision
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        return precision;
    }
        
    /**
     * Scale validator for {@code DtNumber}.
     * 
     * @param scale is scale supplied on column creation
     * @return specified scale
     */
    static public Optional<Short> validateScale(
            Optional<Short> scale) {
        return scale;
    }
        
    /**
     * Marks {@code DtNumber} as default for non-integer SQL types.
     * 
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 10 if {@code DtNumber} can be used for column and -1 otherwise
    */
    public static int eligibleForSqlType(int sqlType
            , Optional<Integer> precision, Optional<Short> scale
            , boolean isNullable, String name) {
        if (((sqlType == Types.BIGINT)
                || (sqlType == Types.DECIMAL) || (sqlType == Types.DOUBLE)
                || (sqlType == Types.FLOAT) || (sqlType == Types.NUMERIC)
                || (sqlType == Types.REAL))) {
            return 9;
        }
        return -1;
    }

    private static final DtOptNumber EMPTY = new DtOptNumber();
    
    protected DtOptNumber() {
        super();
    }

    /**
     * Returns an empty {@code DtOptNumber} instance.  No value is present for
     * this Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtOptNumber}
     */
    public static DtOptNumber empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptNumber} with the specified present non-null
     * BigDecimal value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptNumber} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptNumber of(BigDecimal value) {
        Objects.requireNonNull(value);
        return new DtOptNumber(value);
    }

    /**
     * Returns an {@code DtOptNumber} describing the specified BigDecimal value,
     * if non-null, otherwise returns an empty {@code DtOptNumber}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptNumber} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptNumber}
     */
    public static DtOptNumber ofNullable(BigDecimal value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Returns an {@code DtOptNumber} with the specified present non-null
     * float value.
     *
     * @param value the value to be present
     * @return an {@code DtOptNumber} with the value present
     */
    public static DtOptNumber of(float value) {
        return new DtOptNumber(value);
    }

    /**
     * Returns an {@code DtOptNumber} with the specified present non-null
     * double value.
     *
     * @param value the value to be present
     * @return an {@code DtOptNumber} with the value present
     */
    public static DtOptNumber of(double value) {
        return new DtOptNumber(value);
    }

    /**
     * Returns an {@code DtOptNumber} with the specified long value.
     *
     * @param value the value to be present
     * @return an {@code DtOptNumber} with the value present
     */
    public static DtOptNumber of(long value) {
        return new DtOptNumber(value);
    }

    /**
     * Creates optional provys number value from supplied value.
     * @param value - value new object will be initialised to
     */
    private DtOptNumber(BigDecimal value) {
        super(value);
    }
    
    /**
     * Creates optional provys number value from supplied float value.
     * @param value - value new object will be initialised to
     */
    private DtOptNumber(float value) {
        super(BigDecimal.valueOf(value));
    }
    
    /**
     * Creates optional provys number value from supplied double value.
     * @param value - value new object will be initialised to
     */
    private DtOptNumber(double value) {
        super(BigDecimal.valueOf(value));
    }
    
    /**
     * Creates optional provys number value from supplied long value.
     * @param value - value new object will be initialised to
     */
    private DtOptNumber(long value) {
        super(BigDecimal.valueOf(value));
    }
    
    @Override
    public String toStringValue(){
        return getValue().map(value -> value.toPlainString()).orElse("");
    }

    @Override
    public String toString(){
        return getValue().map(value -> value.toPlainString()).orElse("");
    }
    
    @Override
    public boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtOptNumber) {
            return getValue().equals(((DtOptNumber) secondObject).getValue());
        } else if (isPresent() && (secondObject instanceof DtNumber)) {
            return get().equals(((DtNumber) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        if (!isPresent()) {
            return 0;
        }
        return map(val -> val.hashCode()).orElse(0);
    }

    @Override
    public Optional<Double> getDouble() {
        return map((val) -> val.doubleValue());
    }

}
