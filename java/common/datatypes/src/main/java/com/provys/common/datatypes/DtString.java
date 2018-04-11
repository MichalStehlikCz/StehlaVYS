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
 * 
 * Used to store PROVYS VARCHAR and NOTE values. Also ancestor for name and
 * name_nm subtypes
 */
@JsonbTypeSerializer(JsonbDtSerializer.class)
@JsonbTypeDeserializer(JsonbDtStringDeserializer.class)
public class DtString extends Dt{

    public class StringTooLongException extends ProvysException {
        public StringTooLongException(String value) {
            super("String too long ("+String.valueOf(value.length())
                    +" characters)");
        }
    }

    static final long serialVersionUID = 1L;
    
    private final String value;
    
    public DtString(String value){
        if ((value == null) || (value.equals(""))) {
            throw new Dt.NullValueNotSupportedException();
        }
        if (value.length()>4000) {
            throw new StringTooLongException(value);
        }
        this.value=value;
    }
    
    @Override
    public String getValue(){
        return this.value;
    }
    
    @Override
    public String toString(){
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
            return this.value.equals(((DtString) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }
}
