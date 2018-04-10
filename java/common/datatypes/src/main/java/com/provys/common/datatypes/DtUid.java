/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;

/**
 *
 * @author stehlik
 */
@JsonbTypeSerializer(JsonbDtSerializer.class)
@JsonbTypeDeserializer(JsonbDtUidDeserializer.class)
public class DtUid implements Dt{

    private final String value;
    
    public DtUid(String value){
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
    public boolean equals(Object anObject){
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof DtString) {
            return this.value.equals(((DtString) anObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }
}
