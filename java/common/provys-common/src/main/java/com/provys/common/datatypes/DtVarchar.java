/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import static java.lang.String.valueOf;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author micha
 */
@JsonbTypeAdapter(JsonbDtVarcharAdapter.class)
public class DtVarchar extends DtString {

    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    private static final long serialVersionUID = 1L;

    /**
     * Register DtVarchar type to Dt types repository.
     */
    static void register() {
        final List<Integer> defaultForSqlTypes = new ArrayList<>(4);
        defaultForSqlTypes.add(Types.CHAR);
        defaultForSqlTypes.add(Types.NCHAR);
        defaultForSqlTypes.add(Types.NVARCHAR);
        defaultForSqlTypes.add(Types.VARCHAR);
        DtRepository.registerDtType(DtBoolean.class, Types.VARCHAR
                , Optional.of(4000), defaultForSqlTypes);
    }
    
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
    @SuppressWarnings("PublicInnerClass")
    static public class VarcharTooLongException extends ProvysException {

        private static final long serialVersionUID = 1L;

        /**
         * Initializes varchar too long exception with predefined message
         * @param value is supplied (too long) value
         */
        public VarcharTooLongException(String value) {
            super("String too long (" + valueOf(value.length()) + " characters)");
        }
    }
    
}
