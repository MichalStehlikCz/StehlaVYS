/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysException;
import java.sql.Types;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

/**
 * Definition of result column for SELECT statements.
 * Uses Dt datatypes to describe column type. Can define column size for CHAR
 * and VARCHAR columns, tries to deduct it from type.
 * 
 * @author stehlik
 */
public class ColumnDef {


    private static String validateType(String type) {
        Class<? extends Dt> typeClass;
        try {
            typeClass = Class
                    .forName("com.provys.common.datatypes."+type)
                    .asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
        return typeClass.getSimpleName();
    }

    private static int validateSize(String type, int size) {
        int defSize = size;
        switch (type) {
            case "DtBoolean":
                defSize = 1;
                break;
            case "DtInteger":
                defSize = -1;
                break;
            case "DtName":
            case "DtNameNm":
                if (defSize == -1) {
                    defSize = 200;
                }
                break;
            case "DtNumber":
            case "DtUid":
                defSize = -1;
                break;
            case "DtVarchar":
                if (defSize == -1) {
                    defSize = 4000;
                }
                break;
            default:
                throw new UnsupportedColumnDatatypeException(type);
        }
        return defSize;
    }
    
    private static int validateSize(String type) {
        return validateSize(type, -1);
    }
    private final String name;
    private final String type;
    private final int size;

    /**
     * Create column definition based on name and type.
     * 
     * @param name
     * @param type
     */
    public ColumnDef(String name, String type) {
        this.name = name;
        this.type = validateType(type);
        this.size = validateSize(this.type);
    }
    
    /**
     * Create column definition based on name, type and column size.
     * 
     * @param name
     * @param type
     * @param size
     */
    @JsonbCreator
    public ColumnDef(@JsonbProperty("name") String name
            , @JsonbProperty("type") String type
            , @JsonbProperty("size") int size) {
        this.name = name;
        this.type = validateType(type);
        this.size = validateSize(this.type, size);
    }

    /**
     * Create column definition based on type.
     * 
     * @param type
     */
    public ColumnDef(String type) {
        this.name = null;
        this.type = validateType(type);
        this.size = validateSize(this.type);
    }
    
    /**
     * Create column definition based on type and column size.
     * 
     * @param type
     * @param size
     */
    public ColumnDef(String type, int size) {
        this.name = null;
        this.type = validateType(type);
        this.size = validateSize(this.type, size);
    }

    /**
     * Create column definition based on name and class.
     * @param name
     * @param typeClass
     */
    public ColumnDef(String name, Class<? extends Dt> typeClass) {
        this.name = name;
        this.type = typeClass.getSimpleName();
        this.size = validateSize(this.type);
    }
    
    /**
     * Create column definition based on name, class and column size.
     * 
     * @param name
     * @param typeClass
     * @param size
     */
    public ColumnDef(String name, Class<? extends Dt> typeClass, int size) {
        this.name = name;
        this.type = typeClass.getSimpleName();
        this.size = validateSize(this.type, size);
    }

    /**
     * Create column definition based on class.
     * 
     * @param typeClass
     */
    public ColumnDef(Class<? extends Dt> typeClass) {
        this.name = null;
        this.type = typeClass.getSimpleName();
        this.size = validateSize(this.type);
    }
    
    /**
     * Create column definition based on class and column size.
     * 
     * @param typeClass
     * @param size
     */
    public ColumnDef(Class<? extends Dt> typeClass, int size) {
        this.name = null;
        this.type = typeClass.getSimpleName();
        this.size = validateSize(this.type, size);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the type as class (subclass of Dt)
     */
    @JsonbTransient
    public Class<? extends Dt> getTypeClass() {
        Class<? extends Dt> typeClass;
        try {
            typeClass = Class
                    .forName("com.provys.common.datatypes." + type)
                    .asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
        return typeClass;
    }

    /**
     * @return the size corresponding to column type
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Return SQL Type (java.sql.Types) correspnding to column type.
     * 
     * @return SQL type constant corresponding to actual type
     */
    @JsonbTransient
    public int getSqlType() {
        switch (type) {
            case "DtBoolean":
                return Types.CHAR;
            case "DtInteger":
                return Types.INTEGER;
            case "DtName":
            case "DtNameNm":
                return  Types.VARCHAR;
            case "DtNumber":
                return Types.DECIMAL;
            case "DtRowId":
                return Types.ROWID;
            case "DtUid":
                return Types.DECIMAL;
            case "DtVarchar":
                return Types.VARCHAR;
            case "Boolean":
                return Types.BOOLEAN;
            case "Byte":
                return Types.INTEGER;
            case "Short":
                return Types.INTEGER;
            case "Integer":
                return Types.INTEGER;
            case "Long":
                return Types.BIGINT;
            case "Float":
                return Types.FLOAT;
            case "Double":
                return Types.DOUBLE;
            case "BigDecimal":
                return Types.DECIMAL;
            case "String":
                return Types.VARCHAR;
            case "Date":
                return Types.DATE;
            case "Time":
                return Types.TIME;
            case "Timestamp":
                return Types.TIMESTAMP;
            default:
                throw new UnsupportedColumnDatatypeException(this.type);
        }
    }
    
    /**
     * Exception raised when value supplied to ColumnDef is not one of supported
     * types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedColumnDatatypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedColumnDatatypeException(String type) {
            super("Unsupported class for column definition: "+type);
        }
    }

    /**
     * Exception raised when SQL type supplied to ColumnDef is not one of
     * supported types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedSqlTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedSqlTypeException(int sqlType) {
            super("Unsupported SQL type code for column definition: "
                    +sqlType);
        }
    }

    /**
     * Exception raised when type supplied to ColumnDef (as string) is not one
     * of supported types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedTypeException(String type, Throwable cause) {
            super("Unsupported type for column definition: "+type, cause);
        }
    }

}
