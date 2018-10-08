/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
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
    private String type;
    private Dt value = null;

    /**
     * Default constructor for BindValue
     */
    public BindValue() {};
    
    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param typeClass is data type of bind variable (class)
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, Class<? extends Dt> typeClass, Dt value) {
        this.name = name;
        this.value = value;
        setType(typeClass);
    }

    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param type is data type of bind variable (as string)
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, String type, Dt value) {
        this.name = name;
        this.value = value;
        setType(type);
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
        this.deriveTypeFromValue();
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return type as Class
     */
    public Class<? extends Dt> getTypeClass() {
        try {
            return Class.forName("com.provys.common.datatypes"+type)
                    .asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
    }

    /**
     * Set type of bind to correspond to supplied class.
     * @param type is class acceptable as value for given bind
     */
    @SuppressWarnings("FinalMethod")
    public final void setType(Class<? extends Dt> type) {
        this.type = type.getSimpleName();
        checkType();
    }
    
    /**
     * Setter method for type.
     * Method finds corresponding class and calls setType method with class
     * parameter
     * 
     * @param type sets type field
     */
    @SuppressWarnings("FinalMethod")
    public final void setType(String type) {
        Class<? extends Dt> typeClass;
        try {
            typeClass = Class
                    .forName("com.provys.common.datatypes"+type)
                    .asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
        setType(typeClass);
    }
    
    /**
     * @return the value
     */
    public Dt getValue() {
        return value;
    }

    private void deriveTypeFromValue() {
        if (value != null) {
            setType(value.getClass());
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
        if ((this.type == null) && (this.value != null)) {
            deriveTypeFromValue();
        } else {
            checkType();
        }
    }

    /**
     * Check value against type and report mismatch.
     */
    private void checkType() {
        if ((type != null) & (value != null)) {
            if (!getTypeClass().isInstance(value)) {
                throw new IncompatibleValueAndTypeException(getTypeClass()
                        , value.getClass());
            }
        }
    }

    /**
     * Exception raised when type supplied to ColumnDef (as string) is not one
     * of supported types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedTypeException extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedTypeException(String type, Throwable cause) {
            super("Unsupported type for column definition: "+type, cause);
        }
    }
    
    /**
     * Exception raised when assigned value and type do not match.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class IncompatibleValueAndTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        IncompatibleValueAndTypeException(Class<?> type, Class<?> valuetype) {
            super("Value and type mismatch; type: " + type.getSimpleName()
                    + ", value" + valuetype.getSimpleName());
        }
    }
    
}
