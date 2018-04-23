/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import java.io.Serializable;

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
    private Class<?> datatype;
    private Object value;

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
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
