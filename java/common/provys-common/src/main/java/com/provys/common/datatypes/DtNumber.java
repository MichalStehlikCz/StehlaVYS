/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author stehlik
 * 
 * Used to store NUMBER values
 */
@JsonbTypeAdapter(JsonbDtNumberAdapter.class)
public class DtNumber implements DtNumeric{

    private static final long serialVersionUID = 1L;

    /**
     * Register DtNumber type to Dt types repository.
     */
    static void register() {
        final List<Integer> defaultForSqlTypes = new ArrayList<>(6);
        defaultForSqlTypes.add(Types.BIGINT);
        defaultForSqlTypes.add(Types.DECIMAL);
        defaultForSqlTypes.add(Types.DOUBLE);
        defaultForSqlTypes.add(Types.FLOAT);
        defaultForSqlTypes.add(Types.NUMERIC);
        defaultForSqlTypes.add(Types.REAL);
        DtRepository.registerDtType(DtNumber.class, Types.NUMERIC
                , Optional.empty(), defaultForSqlTypes);
    }
    
    private final BigDecimal value;
    
    /**
     * Creates provys number value from supplied value.
     * @param value - value new object will be initialised to
     */
    public DtNumber(BigDecimal value) {
        if (value == null) {
            throw new Dt.NullValueNotSupportedException();
        }
        this.value=value;
    }
    
    /**
     * Creates provys number value from supplied float value.
     * @param value - value new object will be initialised to
     */
    public DtNumber(float value) {
        this.value=new BigDecimal(value);
    }
    
    /**
     * Creates provys number value from supplied double value.
     * @param value - value new object will be initialised to
     */
    public DtNumber(double value) {
        this.value=new BigDecimal(value);
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
        if (secondObject == null) {
            return false;
        }
        if (this.getClass().equals(secondObject.getClass()))
        {
            return this.value.equals(((DtNumber) secondObject).getValue());
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
