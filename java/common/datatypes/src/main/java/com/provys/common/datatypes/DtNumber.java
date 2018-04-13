/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.math.BigDecimal;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author stehlik
 * 
 * Used to store NUMBER values
 */
@JsonbTypeAdapter(JsonbDtNumberAdapter.class)
public class DtNumber extends DtNumeric{

    private final BigDecimal value;
    
    /**
     * Creates provys number value from supplied value
     * @param value - value new object will be initialised to
     */
    public DtNumber(BigDecimal value) {
        if (value == null) {
            throw new Dt.NullValueNotSupportedException();
        }
        this.value=value;
    }
    
    /**
     * Getter method for value
     * @return value representing effective value of this provzs number
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
        if (this.getClass().getName().equals(secondObject.getClass().getName()))
        {
            return this.value.equals(((DtNumber) secondObject).value);
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
