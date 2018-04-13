/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import static java.lang.String.valueOf;
import javax.json.bind.annotation.JsonbTypeAdapter;


@JsonbTypeAdapter(JsonbDtNameAdapter.class)
public class DtVarchar extends DtString {

    /**
     *
     * @param value
     */
    public DtVarchar(String value){
        super(value);
        if (value.length()>4_000) {
            throw new VarcharTooLongException(value);
        }
    }

    /**
     * Varchar value too long.
     * Exception raised when  varchar value length exceeds 4000; such value
     * is not supported by server and would fail to store (it should be
     * 4000 bytes in server charset... but 4000 characters as reasonable
     * approximation)
     */
    public class VarcharTooLongException extends ProvysException {

        /**
         * Initializes varchar too long exception with predefined message
         * @param value is supplied (too long) value
         */
        public VarcharTooLongException(String value) {
            super("String too long (" + valueOf(value.length()) + " characters)");
        }
    }
    
}
