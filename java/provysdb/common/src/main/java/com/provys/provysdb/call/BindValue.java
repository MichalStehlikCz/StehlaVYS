/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

/**
 * BindValue is used to prepare parameters for CallableStatement.
 * Bind value represents bind variable (identified by name), its type and
 * value to be supplied.
 * 
 * @author stehlik
 */
public class BindValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    @JsonbTransient
    private Class<?> datatype;
    private Dt value = null;

    /**
     * Default constructor for BindValue
     */
    public BindValue() {};
    
    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param datatype is data type of bind variable (class)
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, Class<?> datatype, Dt value) {
        this.name = name;
        this.datatype = datatype;
        this.value = value;
    }

    /**
     * Constructor for BindValue with specification of name and value.
     * Datatype is taken from value. Note that value should not be null for this
     * constructor to work properly
     * 
     * @param name is name of bind variable
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, Dt value) {
        this.name = name;
        this.value = value;
        this.deriveDatatypeFromValue();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the datatype
     */
    public Class<?> getDatatype() {
        return datatype;
    }

    /**
     * @param datatype the datatype to set
     */
    public void setDatatype(Class<?> datatype) {
        this.datatype = datatype;
    }

    /**
     * @return the value
     */
    public Dt getValue() {
        return value;
    }

    private void deriveDatatypeFromValue() {
        if (value != null) {
            datatype = value.getClass();
        }
    }

    /**
     * Sets value of value field.
     * If datatype was not previously specified and supplied value is not null,
     * procedure also sets datatype field
     * 
     * @param value the value to set
     */
    public void setValue(Dt value) {
        this.value = value;
        if ((this.datatype == null) && (this.value != null)) {
            deriveDatatypeFromValue();
        }
    }
}
