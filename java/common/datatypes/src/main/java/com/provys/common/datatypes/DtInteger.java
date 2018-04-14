/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Used to store INTEGER values (corresponding to NUMBER(5, 0) in Oracle)
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtIntegerAdapter.class)
public class DtInteger extends DtNumeric{

    private static final long serialVersionUID = 1L;

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
