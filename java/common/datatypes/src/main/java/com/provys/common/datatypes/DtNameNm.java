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
 * @author micha
 */
@JsonbTypeSerializer(JsonbDtNameNmSerializer.class)
@JsonbTypeDeserializer(JsonbDtNameNmDeserializer.class)
public class DtNameNm extends DtName{

    static final long serialVersionUID = 1L;

    public DtNameNm(String value){
        super(value);
    }
}
