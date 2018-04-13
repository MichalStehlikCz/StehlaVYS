/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author stehlik
 * 
 * Used to store PROVYS UID values. UID domain is implemented as 38 digit
 * NUMBER in Oracle, but should be treated as not interpreted identifier and
 * thus String type has been chosen to hold value of UID in PROVYS
 */
@JsonbTypeAdapter(JsonbDtBooleanAdapter.class)
public class DtBoolean extends Dt{

    private final boolean value;

    /**
     * Default constructor - creates DtBoolean from supplied value
     * @param value - supplied value new DtBoolean will be set to 
     */    
    public DtBoolean(boolean value){
        this.value=value;
    }

    /**
     * Value retrieval
     * @return returns boolean value, corresponding to this object's effective
     * value
     */    
    public boolean getValue() {
        return this.value;
    }
    
    @Override
    public String toStringValue(){
        return Boolean.toString(this.value);
    }

    @Override
    public String toString(){
        return Boolean.toString(this.value);
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
            return this.value == ((DtBoolean) secondObject).value;
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return Boolean.hashCode(this.value);
    }
}
