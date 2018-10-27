/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Used to store NUMBER values.
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtNumberAdapter.class)
public class DtNumber implements DtNumeric{

    /**
     * Register DtNumber type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtNumber.class, Types.NUMERIC
                , DtNumber::validatePrecision, DtNumber::validateScale
                , DtNumber::eligibleForSqlType);
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
        if ((!isNullable) && ((sqlType == Types.BIGINT)
                || (sqlType == Types.DECIMAL) || (sqlType == Types.DOUBLE)
                || (sqlType == Types.FLOAT) || (sqlType == Types.NUMERIC)
                || (sqlType == Types.REAL))) {
            return 10;
        }
        return -1;
    }

    private final BigDecimal value;
    
    /**
     * Returns provys number value representing supplied BigDecimal value.
     * 
     * @param value - value new object will be initialised to
     * @return {@code DtNumber} value representing supplied number
     */
    public static DtNumber of(BigDecimal value) {
        return new DtNumber(value);
    }
    
    /**
     * Returns provys number value representing supplied float value.
     * 
     * @param value - value new object will be initialised to
     * @return {@code DtNumber} value representing supplied number
     */
    public static DtNumber of(float value) {
        return new DtNumber(BigDecimal.valueOf(value));
    }
    
    /**
     * Returns provys number value representing supplied double value.
     * 
     * @param value - value new object will be initialised to
     * @return {@code DtNumber} value representing supplied number
     */
    public static DtNumber of(double value) {
        return new DtNumber(BigDecimal.valueOf(value));
    }
    
    private DtNumber(BigDecimal value) {
        if (value == null) {
            throw new Dt.NullValueNotSupportedException();
        }
        this.value=value;
    }
    
    /**
     * Getter method for value
     * @return value representing effective value of this provys number
     */
    public BigDecimal getValue() {
        return this.value;
    }
    
    @Override
    public String toStringValue(){
        return this.value.toPlainString();
    }

    @Override
    public String toString(){
        return this.value.toPlainString();
    }
    
    @Override
    public boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtNumber) {
            return getValue().equals(((DtNumber) secondObject).getValue());
        }
        if (secondObject instanceof DtOptNumber) {
            return ((DtOptNumber) secondObject)
                    .map(val -> val.equals(getValue())).orElse(false);
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

    @Override
    public double getDouble() {
        return this.value.doubleValue();
    }
}
