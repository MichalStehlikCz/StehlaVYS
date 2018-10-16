/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.sql.Types;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Represents value of provys domain NAME.
 * NAME domain is stored as VARCAR2(200) in Oracle database. It is mostly
 * used on properties, that contain descriptive name of an object
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtNameAdapter.class)
public class DtName extends DtString {

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    static final long serialVersionUID = 2L;

    /**
     * Register DtName type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtName.class, Types.VARCHAR
                , Optional.of(200));
    }
    
    /**
     * Creates DtName from supplied value. Verifies, that supplied value is
     * non-empty string not longer than 200 characters
     *
     * @param value - string value new object will be initialised with
     */
    public DtName(String value) {
        super(value);
        if (value.length() > 200) {
            throw new NameTooLongException();
        }
    }

    /**
     * Exception raised when value supplied to provys name is longer than 200
     * characters
     */
    @SuppressWarnings("PublicInnerClass")
    static public class NameTooLongException extends ProvysException {

        private static final long serialVersionUID = 1L;

        NameTooLongException() {
            super("Name cannot exceed 200 characters");
        }
    }

}
