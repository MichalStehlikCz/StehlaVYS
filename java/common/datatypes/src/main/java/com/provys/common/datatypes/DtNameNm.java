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
 * Used to store PROVYS VARCHAR and NOTE values. Also ancestor for name and
 * name_nm subtypes
 */
@JsonbTypeAdapter(JsonbDtNameNmAdapter.class)
public class DtNameNm extends DtName{

    static final long serialVersionUID = 3L;

    /**
     * Creates new DtNameNm object from supplied value. Verifies that supplied
     * value is valid as internal name
     * @param value - val new internal name will be set to
     */
    public DtNameNm(String value){
        super(value);
        if (value.length()>200){
            throw new NameTooLongException();
        }
    }
}
