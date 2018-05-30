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

/**
 *
 * @author stehlik
 */
public class ColumnDef implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Class<? extends Dt> type;
    private int size = -1;
    private String name;

    public ColumnDef() {};
    
    public ColumnDef(Class<? extends Dt> type) {
        this.type = type;
    }

    public ColumnDef(Class<? extends Dt> type, int size) {
        this.type = type;
        this.size = size;
    }

    /**
     * @return the type
     */
    public Class<? extends Dt> getType() {
        return type;
    }

    /**
     * Set type of column to correspond to supplied class
     * @param type is class column should be stored in
     */
    public void setType(Class<? extends Dt> type) {
        this.type = type;
        switch (type.getSimpleName()) {
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
                throw new UnsupportedColumnDatatypeException(type);
        }
    }
    
    /**
     * Set type of column to correspond to supplied SQL type; PROVYS datatype
     * is guessed from data, parsed from query
     * @param type is sql datatype parsed from supplied statement
     */
    public void setType(int type) {
        switch (type) {
            case Types.CHAR:
                this.type = DtVarchar.class;
                break;
            case Types.DATE:
                throw new UnsupportedSqlTypeException(type);
            case Types.BIGINT:
            case Types.DECIMAL:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
                this.type = DtNumber.class;
                break;
            case Types.INTEGER:
                this.type = DtInteger.class;
                break;
            case Types.ROWID:
                this.type = DtRowId.class;
                break;
            case Types.VARCHAR:
                this.type = DtVarchar.class;
                break;
            default:
                throw new UnsupportedSqlTypeException(type);
        }
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

    public int getSqlType() {
        switch (type.getSimpleName()) {
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

        UnsupportedColumnDatatypeException(Class<?> datatype) {
            super("Unsupported class for column definition: "
                    +datatype.getSimpleName());
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

}
