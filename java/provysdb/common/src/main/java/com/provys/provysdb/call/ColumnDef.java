/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysException;
import java.io.Serializable;
import java.sql.Types;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author stehlik
 */
public class ColumnDef implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String type;
    private int size = -1;
    private String name;

    public ColumnDef() {};
    
    public ColumnDef(Class<? extends Dt> typeClass) {
        setType(typeClass);
    }

    public ColumnDef(Class<? extends Dt> typeClass, int size) {
        setType(typeClass);
        this.size = size;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set type of column to correspond to supplied class
     * @param type is class column should be stored in
     */
    @SuppressWarnings("FinalMethod")
    public final void setType(Class<? extends Dt> type) {
        this.type = type.getSimpleName();
        switch (this.type) {
            case "DtBoolean":
                size = 1;
                break;
            case "DtInteger":
                size = -1;
                break;
            case "DtName":
            case "DtNameNm":
                size = 200;
                break;
            case "DtNumber":
            case "DtRowId":
            case "DtUid":
                size = -1;
                break;
            case "DtVarchar":
                size = 4000;
                break;
            default:
                throw new UnsupportedColumnDatatypeException(this.type);
        }
    }
    
    /**
     * Set type of column to correspond to supplied SQL type; PROVYS datatype
     * is guessed from data, parsed from query
     * @param type is sql datatype parsed from supplied statement
     */
    public void setType(int type) {
        Class<? extends Dt> typeClass;
        switch (type) {
            case Types.CHAR:
                typeClass = DtVarchar.class;
                break;
            case Types.DATE:
                throw new UnsupportedSqlTypeException(type);
            case Types.BIGINT:
            case Types.DECIMAL:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
                typeClass = DtNumber.class;
                break;
            case Types.INTEGER:
                typeClass = DtInteger.class;
                break;
            case Types.ROWID:
                typeClass = DtRowId.class;
                break;
            case Types.VARCHAR:
                typeClass = DtVarchar.class;
                break;
            default:
                throw new UnsupportedSqlTypeException(type);
        }
        setType(typeClass);
    }
    
    /**
     * Setter method for type.
     * Method finds corresponding class and calls setType method with class
     * parameter
     * 
     * @param type sets type field
     */
    public void setType(String type) {
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
     * @return the size corresponding to column type
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
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
