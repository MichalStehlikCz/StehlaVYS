/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.error.ProvysException;
import java.io.Serializable;
import java.sql.Types;

/**
 *
 * @author stehlik
 */
public class ColumnDef implements Serializable {
    private int type;
    private int size = -1;

    public ColumnDef() {};
    
    public ColumnDef(int type) {
        this.type = type;
    }
    
    public ColumnDef(int type, int size) {
        this.type = type;
        this.size = size;
    }
    
    public ColumnDef(Class<?> typeClass) {
        this.setType(typeClass);
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Set type of column to correspond to supplied class
     * @param typeClass is class column should be stored in
     */
    public final void setType(Class<?> typeClass) {
        switch (typeClass.getSimpleName()) {
            case "DtBoolean":
                type = Types.CHAR;
                size = 1;
                break;
            case "DtInteger":
                type = Types.INTEGER;
                break;
            case "DtName":
            case "DtNameNm":
                type = Types.VARCHAR;
                size = 200;
                break;
            case "DtNumber":
            case "DtUid":
                type = Types.DECIMAL;
                break;
            case "DtVarchar":
                type = Types.VARCHAR;
                size = 4000;
                break;
            case "Boolean":
                type = Types.BOOLEAN;
                break;
            case "Byte":
                type = Types.INTEGER;
                break;
            case "Short":
                type = Types.INTEGER;
                break;
            case "Integer":
                type = Types.INTEGER;
                break;
            case "Long":
                type = Types.BIGINT;
                break;
            case "Float":
                type = Types.FLOAT;
                break;
            case "Double":
                type = Types.DOUBLE;
                break;
            case "BigDecimal":
                type = Types.DECIMAL;
                break;
            case "String":
                type = Types.VARCHAR;
                size = 4000;
                break;
            case "Date":
                type = Types.DATE;
                break;
            case "Time":
                type = Types.TIME;
                break;
            case "Timestamp":
                type = Types.TIMESTAMP;
                break;
            default:
                throw new UnsupportedColumnDatatypeException(typeClass);
        }
    }
    /**
     * @return the size
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
     * Exception raised when value supplied to Bind is not one of supported
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
}
