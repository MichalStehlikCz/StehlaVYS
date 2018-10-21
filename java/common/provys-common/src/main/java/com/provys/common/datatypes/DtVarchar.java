/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import static java.lang.String.valueOf;
import java.sql.Types;
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
        DtRepository.registerDtType(DtVarchar.class, Types.VARCHAR
                , DtVarchar::validatePrecision, DtVarchar::eligibleForSqlType);
    }
    
    static private final Optional<Integer> DEFAULTPRECISION = Optional.of(4000);
    
    /**
     * Precision validator for {@code DtVarchar}.
     * 
     * @param precision is precision supplied on column creation
     * @return precision specified, 4000 if nothing has been specified
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        if (precision.isPresent()) {
            return precision;
        }
        return DEFAULTPRECISION;
    }
        
    /**
     * Marks {@code DtVarchar} as default for mandatory string SQL types.
     * 
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 10 if {@code DtVarchar} can be used for column and -1 otherwise
    */
    public static int eligibleForSqlType(int sqlType
            , Optional<Integer> precision, Optional<Short> scale
            , boolean isNullable, String name) {
        if ((!isNullable) && ((sqlType == Types.CHAR)
                || (sqlType == Types.NCHAR) || (sqlType == Types.NVARCHAR)
                || (sqlType == Types.VARCHAR))) {
            return 10;
        }
        return -1;
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
