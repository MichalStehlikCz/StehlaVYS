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
 * 
 * Used to store PROVYS VARCHAR and NOTE values. Also ancestor for name and
 * name_nm subtypes
 */
@JsonbTypeSerializer(JsonbDtSerializer.class)
@JsonbTypeDeserializer(JsonbDtNameNmDeserializer.class)
public class DtNameNm extends DtName{

    static final long serialVersionUID = 3L;

    public DtNameNm(String value){
        super(value);
        if (value.length()>200){
            throw new NameTooLongException();
        }
    }
}
