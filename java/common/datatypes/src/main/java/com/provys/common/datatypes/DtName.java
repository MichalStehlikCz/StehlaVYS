/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;
import com.provys.common.error.ProvysException;
/**
 *
 * @author stehlik
 * 
 * Used to store PROVYS VARCHAR and NOTE values. Also ancestor for name and
 * name_nm subtypes
 */
@JsonbTypeSerializer(JsonbDtSerializer.class)
@JsonbTypeDeserializer(JsonbDtNameDeserializer.class)
public class DtName extends DtString{

    static final long serialVersionUID = 2L;

    class NameTooLongException extends ProvysException {
        public NameTooLongException() {
            super("Name cannot exceed 200 characters");
        }
    }

    public DtName(String value){
        super(value);
        if (value.length()>200){
            throw new NameTooLongException();
        }
    }
    
}
