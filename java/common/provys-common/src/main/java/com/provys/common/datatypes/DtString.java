/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

/**
 * Common abstract ancestor for all string holding Dt types.
 * Implements equals method that considers values equal when strings are equal.
 * Descendants should generally not override equals method, as it might break
 * comparison transitivity
 * 
 * @author stehlik
 */
abstract public class DtString implements Dt{

    private final String value;
    
    /**
     * Creates provys string based on supplied value
     * @param value that will be assigned to newly created provys string object
     */
    public DtString(String value){
        if ((value == null) || (value.isEmpty())) {
            throw new Dt.NullValueNotSupportedException();
        }
        this.value=value;
    }
    
    /**
     * Getter method for value / effective value of provys string object
     * @return effective value of provys string object
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
    public String toSqlLiteral() {
        // replace ', newline and CHR(0) characters to prevent SQL injection
        return "'" + this.value.replace("'", "''")
                .replace("\n", "'||CHR(10)||'")
                .replace("\0", "\\0") + "'";
    }
    
    @Override
    public final boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtString) {
            return getValue().equals(((DtString) secondObject).getValue());
        }
        if (secondObject instanceof DtOptString) {
            if (!((DtOptString) secondObject).isPresent()) {
                return false;
            }
            return getValue().equals(((DtOptString) secondObject).get());
        }
        return false;
    }
    
    @Override
    public final int hashCode(){
        return getValue().hashCode();
    }
}
