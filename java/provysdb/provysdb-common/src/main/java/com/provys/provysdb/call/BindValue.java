/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
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

    private Dt value = null;

    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param typeClass is data type of bind variable (class)
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, Class<? extends Dt> typeClass, Dt value) {
        super(name, typeClass);
        this.value = value;
        checkType();
    }

    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param type is data type of bind variable (as string)
     * @param value is value to be passed to bind variable
     */
    public BindValue(String name, String type, Dt value) {
        super(name, type);
        this.value = value;
        checkType();
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
        super(name, value.getClass());
        this.value = value;
        checkType();
    }

    /**
     * @return the value
     */
    public Dt getValue() {
        return value;
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
        checkType();
    }

    /**
     * Check value against type and report mismatch.
     */
    @SuppressWarnings("FinalPrivateMethod")
    private final void checkType() {
        if (value != null) {
            if (!getTypeClass().isInstance(value)) {
                throw new IncompatibleValueAndTypeException(getTypeClass()
                        , value.getClass());
            }
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
