/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.sql.Types;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Used to store INTEGER values (corresponding to NUMBER(5, 0) in Oracle)
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtIntegerAdapter.class)
public class DtInteger implements DtNumeric{

    /**
     * Register DtInteger type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtInteger.class, Types.INTEGER
                , DtInteger::validatePrecision, DtInteger::validateScale
                , DtInteger::eligibleForSqlType);
    }
    
    static private final Optional<Integer> MAXPRECISION = Optional.of(10);
    
    /**
     * Precision validator for {@code DtInteger}.
     * 
     * @param precision is precision supplied on column creation
     * @return specified number or 10 whichever is lower, 10 if not specified
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        if (precision.orElse(11) > 10) {
            return MAXPRECISION;
        }
        return precision;
    }
        
    static private final Optional<Short> SCALE = Optional.of((short) 0);

    /**
     * Scale validator for {@code DtInteger}.
     * 
     * @param scale is scale supplied on column creation
     * @return 0 as integer has scale of zero
     */
    static public Optional<Short> validateScale(
            Optional<Short> scale) {
        return SCALE;
    }
        
    /**
     * Marks {@code DtInteger} as default for integer SQL types.
     * 
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 15 if {@code DtInteger} can be used for column and -1 otherwise
    */
    public static int eligibleForSqlType(int sqlType
            , Optional<Integer> precision, Optional<Short> scale
            , boolean isNullable, String name) {
        if ((!isNullable) && ((sqlType == Types.INTEGER)
                || (sqlType == Types.SMALLINT) || (sqlType == Types.TINYINT)
                || ((scale.map(val -> val<=0).orElse(false))
                && (precision.map(val -> val<=9).orElse(false))
                && (sqlType == Types.NUMERIC)))) {
            return 15;
        }
        return -1;
    }

    private final int value;
    
    /**
     * Creates provys number value from supplied value
     * @param value - value new object will be initialised to
     */
    public DtInteger(int value) {
        this.value=value;
    }
    
    /**
     * Getter method for value
     * @return value representing effective value of this provys integer
     */
    public int getValue() {
        return this.value;
    }
    
    @Override
    public String toStringValue(){
        return Integer.toString(this.value);
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
    
    @Override
    public boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject == null) {
            return false;
        }
        if (this.getClass().equals(secondObject.getClass()))
        {
            return this.value==((DtInteger) secondObject).getValue();
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value;
    }

    @Override
    public double getDouble() {
        return this.value;
    }
}
