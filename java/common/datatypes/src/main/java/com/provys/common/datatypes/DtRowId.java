/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.sql.RowId;
import java.sql.SQLException;
import javax.json.bind.annotation.JsonbTypeAdapter;
import oracle.sql.ROWID;

/**
 * Datatype representing ROWID values.
 * It decorates sql RowId datatype with unified approach of being Dt ancestor
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtRowIdAdapter.class)
public class DtRowId extends Dt{

    private static final long serialVersionUID = 1L;

    private final ROWID value;
    
    /**
     * Creates RowId value from supplied RowId value
     * @param value represents value this object will be assigned
     */
    public DtRowId(RowId value) {
        if (value == null) {
            throw new Dt.NullValueNotSupportedException();
        }
        this.value=(ROWID) value;
    }
    
    /**
     * Creates RowId value from supplied String value
     * @param value represents value this object will be assigned
     */
    public DtRowId(String value) {
        if ((value == null) || (value.isEmpty())) {
            throw new Dt.NullValueNotSupportedException();
        }
        try {
            this.value=new ROWID(value);
        } catch (SQLException ex) {
            throw new InvalidRowIdException(value, ex);
        }
    }
    
    /**
     * Getter method for value - RowId value
     * @return stored value
     */
    public RowId getValue() {
        return this.value;
    }
    
    @Override
    public String toStringValue(){
        return this.value.stringValue();
    }

    @Override
    public String toString(){
        return this.value.toString();
    }
    
    @Override
    public String toSqlLiteral() {
        throw new RowidLiteralNotSupportedException();
    }

    @Override
    public boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject == null) {
            return false;
        }
        if (this.getClass().equals(secondObject.getClass()))
        {
            return this.value.equals(((DtRowId) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

    /**
     * Exception indicating that supplied value is not valid Oracle ROWID
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InvalidRowIdException extends ProvysException {

        private static final long serialVersionUID = 1L;
        /**
         * Creates exception and embeds supplied value to error message
         * + attaches source SQLException as cause
         * @param value - string value that failed to convert to ROWID
         * @param cause - reported SQL exception
         */
        public InvalidRowIdException(String value, SQLException cause) {
            super("Value supplied to Rowid constructor is not valid ROWID ("
                    +value+")", cause);
        }
    }

    /**
     * Raised on attempt to convert RowId to SQL literal.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class RowidLiteralNotSupportedException extends ProvysException {

        private static final long serialVersionUID = 1L;

        public RowidLiteralNotSupportedException() {
            super("System cannot convert Rowid value to SQL literal");
        }
    }
}
