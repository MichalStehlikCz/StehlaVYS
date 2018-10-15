/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysException;
import java.util.Optional;
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
        String name;
        try {
            name = DtRepository.getName(type);
        } catch (DtRepository.UnknownDtTypeException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
        return type;
    }

    private static String validateType(int sqlType) {
        String name;
        try {
            name = DtRepository.getDtBySqlType(sqlType);
        } catch (DtRepository.SqlTypeNotMappedException ex) {
            throw new UnsupportedSqlTypeException(sqlType, ex);
        }
        return name;
    }

    private static int validateSize(String type, int size) {
        int defSize = size;
        if (defSize == -1) {
            defSize = DtRepository.getSqlSize(type);
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
     * Create column definition based on name and class.
     * @param name
     * @param typeClass
     */
    public ColumnDef(String name, Class<? extends Dt> typeClass) {
        this.name = name;
        this.type = validateType(typeClass.getSimpleName());
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
        this.type = validateType(typeClass.getSimpleName());
        this.size = validateSize(this.type, size);
    }

    /**
     * Create column definition based on name, SQL type and column size.
     * 
     * @param name
     * @param sqlType is SQL type code (java.sql.Types) of SQL type for which
     * column should be created
     * @param size
     */
    public ColumnDef(String name, int sqlType, int size) {
        this.name = name;
        this.type = validateType(sqlType);
        this.size = validateSize(this.type, size);
    }

    /**
     * Create column definition based on name and SQL type.
     * 
     * @param name
     * @param sqlType is SQL type code (java.sql.Types) of SQL type for which
     * column should be created
     */
    public ColumnDef(String name, int sqlType) {
        this.name = name;
        this.type = validateType(sqlType);
        this.size = validateSize(this.type);
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
     * @return the type as class (subclass of Dt)
     */
    @JsonbTransient
    public Class<? extends Dt> getTypeClass() {
        return DtRepository.getClass(type);
    }

    /**
     * @return the size corresponding to column type
     */
    public int getSize() {
        return size;
    }

    /**
     * Return SQL Type (java.sql.Types) correspnding to column type.
     * 
     * @return SQL type constant corresponding to actual type
     */
    @JsonbTransient
    public int getSqlType() {
        return DtRepository.getSqlType(this.type);
    }
    
    /**
     * Exception raised when SQL type supplied to ColumnDef is not one of
     * supported types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedSqlTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedSqlTypeException(int sqlType, Throwable cause) {
            super("Unsupported SQL type code for column definition: "
                    + sqlType, cause);
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
            super("Unsupported type for column definition: " + type, cause);
        }
    }

}
