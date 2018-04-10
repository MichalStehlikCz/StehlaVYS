/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;

/**
 *
 * @author stehlik
 */
@JsonbTypeSerializer(JsonbDtSerializer.class)
@JsonbTypeDeserializer(JsonbDtUidDeserializer.class)
public class DtUid extends Dt{

    public class DtUidNotNumberException extends ProvysException {
        public DtUidNotNumberException(String value) {
            super("Value supplied to Uid constructor is not a number ("+value+")");
        }
    }
    
    private final String value;
    
    public DtUid(String value){
        if ((value != null) && !value.matches("\\d+")) {
            throw new DtUidNotNumberException(value);
        }
        this.value=value;
    }
    
    @Override
    public String getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        if (value == null) {
            return "";
        }
        return this.value;
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
            if (this.value == null) {
                return (((DtUid) secondObject).getValue() == null);
            }
            return this.value.equals(((DtUid) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        if (this.value == null) {
            return 0;
        }
        return this.value.hashCode();
    }
}
