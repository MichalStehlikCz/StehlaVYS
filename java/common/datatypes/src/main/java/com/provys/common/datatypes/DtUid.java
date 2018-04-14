/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author micha
 */
@JsonbTypeAdapter(JsonbDtUidAdapter.class)
public class DtUid extends Dt{

    private static final long serialVersionUID = 1L;

    
    private final String value;
    
    /**
     * Creates provys UID value from supplied string
     * @param value represents value this object will be assigned
     */
    public DtUid(String value) {
        if ((value == null) || (value.isEmpty())) {
            throw new Dt.NullValueNotSupportedException();
        }
        if (value.length()>38) {
            throw new UidTooLongException(value);
        }
        if (!value.matches("\\d+")) {
            throw new UidNotNumberException(value);
        }
        this.value=value;
    }
    
    /**
     * Getter method for value - internal String representtaion of UID value
     * @return String value correspoding to this UID
     */
    public String getValue() {
        return this.value;
    }
    
    @Override
    public String toStringValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value;
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
            return this.value.equals(((DtUid) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }
    /**
     * Exception indicating that supplied value is not valid PROVYS Uid, because
     * it cannot be longer than 38 digits
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UidTooLongException extends ProvysException {

        private static final long serialVersionUID = 1L;
        /**
         * Creates exception and embeds supplied value to error message
         * @param value is offending value that is too long
         */
        public UidTooLongException(String value) {
            super("Value supplied to Uid constructor is too long ("+value+")");
        }
    }
    /**
     * Exception indicating that supplied value is not valid PROVYS Uid, because
     * it contains non/numeric characters
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UidNotNumberException extends ProvysException {

        private static final long serialVersionUID = 1L;
        /**
         * Creates exception and embeds supplied value to error message
         * @param value is offending value that is non/numeric
         */
        public UidNotNumberException(String value) {
            super("Value supplied to Uid constructor is not a number ("+value+")");
        }
    }
}
