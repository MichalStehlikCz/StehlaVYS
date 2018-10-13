/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

/**
 * BindVariable is used to prepare parameters for CallableStatement.
 * Bind value represents bind variable (identified by name) with its type. Value
 * should be supplied by other channel for execution of statement (or using
 * its descendant BindValue instead)
 * 
 * @author stehlik
 */
public class BindVariable {

    private final String name;
    private final String type;
   
    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param typeClass is data type of bind variable (class)
     */
    public BindVariable(String name, Class<? extends Dt> typeClass) {
        this.name = name;
        this.type = typeClass.getSimpleName();
    }

    /**
     * Constructor for BindValue with specification of all fields.
     * 
     * @param name is name of bind variable
     * @param type is data type of bind variable (as string)
     */
    @JsonbCreator
    public BindVariable(@JsonbProperty("name") String name
            , @JsonbProperty("type") String type) {
        this.name = name;
        Class<? extends Dt> typeClass;
        try {
            typeClass = Class
                    .forName("com.provys.common.datatypes."+type)
                    .asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
        this.type = typeClass.getSimpleName();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
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
    
}
