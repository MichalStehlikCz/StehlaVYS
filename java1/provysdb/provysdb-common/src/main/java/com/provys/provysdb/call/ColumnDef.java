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
 * and VARCHAR columns, tries to guess precision from type if not supplied.
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

    private final String name;
    private final String type;
    private final Optional<Integer> precision;
    private final Optional<Short> scale;

    /**
     * Create column definition based on name and type.
     * 
     * @param name is name of column
     * @param type is simpleName of {@code Dt} implementation type
     */
    public ColumnDef(String name, String type) {
        this.name = name;
        this.type = validateType(type);
        this.precision = DtRepository.validatePrecision(this.type
                , Optional.empty());
        this.scale = DtRepository.validateScale(this.type, Optional.empty());
    }
    
    /**
     * Create column definition based on name, type and column size.
     * 
     * @param name is name of column
     * @param type is simpleName of {@code Dt} implementation type
     * @param precision is required precision (number of characters / digits)
     */
    public ColumnDef(String name, String type, Optional<Integer> precision) {
        this.name = name;
        this.type = validateType(type);
        this.precision = DtRepository.validatePrecision(this.type, precision);
        this.scale = DtRepository.validateScale(this.type, Optional.empty());
    }

    /**
     * Create column definition based on name, type and column size.
     * 
     * @param name is name of column
     * @param type is simpleName of {@code Dt} implementation type
     * @param precision is required precision (number of characters / digits)
     * @param scale is required scale - number of digits to the right of decimal
     * point
     */
    @JsonbCreator
    public ColumnDef(@JsonbProperty("name") String name
            , @JsonbProperty("type") String type
            , @JsonbProperty("precision") Optional<Integer> precision
            , @JsonbProperty("scale") Optional<Short> scale) {
        this.name = name;
        this.type = validateType(type);
        this.precision = DtRepository.validatePrecision(this.type, precision);
        this.scale = DtRepository.validateScale(this.type, scale);
    }

    /**
     * Create column definition based on name and class.
     * @param name is name of column
     * @param typeClass
     */
    public ColumnDef(String name, Class<? extends Dt> typeClass) {
        this.name = name;
        this.type = validateType(typeClass.getSimpleName());
        this.precision = DtRepository.validatePrecision(this.type
                , Optional.empty());
        this.scale = DtRepository.validateScale(this.type, Optional.empty());
    }
    
    /**
     * Create column definition based on name, class and column size.
     * 
     * @param name is name of column
     * @param typeClass
     * @param precision is required precision (number of characters / digits)
     */
    public ColumnDef(String name, Class<? extends Dt> typeClass
            , Optional<Integer> precision) {
        this.name = name;
        this.type = validateType(typeClass.getSimpleName());
        this.precision = DtRepository.validatePrecision(this.type, precision);
        this.scale = DtRepository.validateScale(this.type, Optional.empty());
    }

    /**
     * Create column definition based on name, SQL type and column size.
     * 
     * @param name is name of column
     * @param sqlType is SQL type code (java.sql.Types) of SQL type for which
     * column should be created
     * @param precision is required precision (number of characters / digits)
     * @param scale is required scale - number of digits to the right of decimal
     * point
     * @param isNullable indicates if column is nullable
     */
    public ColumnDef(String name, int sqlType, Optional<Integer> precision
            , Optional<Short> scale, boolean isNullable) {
        this.name = name;
        this.type = DtRepository.getDtBySqlType(sqlType, precision, scale
                , isNullable, name);
        this.precision = DtRepository.validatePrecision(this.type, precision);
        this.scale = DtRepository.validateScale(this.type, scale);
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
     * @return the precision corresponding to column type
     */
    public Optional<Integer> getPrecision() {
        return precision;
    }

    /**
     * @return the scale corresponding to column type
     */
    public Optional<Short> getScale() {
        return scale;
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
