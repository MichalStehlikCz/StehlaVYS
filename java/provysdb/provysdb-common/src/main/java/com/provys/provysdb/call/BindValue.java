/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * BindValue is used to prepare parameters for CallableStatement.
 * Bind value represents bind variable (identified by name), its type and
 * value to be supplied.
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbBindValueAdapter.class)
public class BindValue extends BindVariable {

    private static final long serialVersionUID = 1L;

    final private Dt value;

    /**
     * Constructor for BindValue with specification of name and value.
     * Datatype is taken from value
     * 
     * @param name is name of bind variable
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, Dt value) {
        super(name, value.getClass());
        this.value = value;
    }

    /**
     * @return the value
     */
    public Dt getValue() {
        return value;
    }

}
