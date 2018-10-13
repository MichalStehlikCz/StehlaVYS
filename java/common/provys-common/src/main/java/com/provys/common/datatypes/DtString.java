/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

/**
 *
 * @author stehlik
 * 
 * Used to store PROVYS VARCHAR and NOTE values. Also ancestor for name and
 * name_nm subtypes
 */
abstract public class DtString extends Dt{

    static final long serialVersionUID = 1L;
    
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
    public boolean equals(Object secondObject){
        if (this == secondObject) {
            return true;
        }
        if (secondObject == null) {
            return false;
        }
        if (this.getClass().equals(secondObject.getClass()))
        {
            return this.value.equals(((DtString) secondObject).getValue());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.value.hashCode();
    }
}
