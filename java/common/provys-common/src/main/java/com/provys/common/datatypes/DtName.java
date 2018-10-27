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

    /**
     * Register DtName type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtName.class, Types.VARCHAR
                , DtName::validatePrecision, DtName::eligibleForSqlType);
    }
    
    static private final Optional<Integer> MAXPRECISION = Optional.of(200);
    
    /**
     * Precision validator for {@code DtName}.
     * 
     * @param precision is precision supplied on column creation
     * @return specified number or 200 whichever is lower, 200 if not specified
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        if (precision.orElse(201) > 200) {
            return MAXPRECISION;
        }
        return precision;
    }
        
    /**
     * Marks {@code DtName} as default for mandatory Strings with length 200.
     * 
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 20 if {@code DtName} can be used for column and -1 otherwise
    */
    public static int eligibleForSqlType(int sqlType
            , Optional<Integer> precision, Optional<Short> scale
            , boolean isNullable, String name) {
        if ((!isNullable) && (sqlType == Types.VARCHAR)
                && (precision.orElse(4000) <= 200)) {
            return 20;
        }
        return -1;
    }

    /**
     * Creates {@code DtName} from supplied value. Verifies, that supplied value
     * is non-empty string not longer than 200 characters
     *
     * @param value - string value new object will be initialised with
     * @return {@code DtName} object for given value
     */
    public static DtName of(String value) {
        return new DtName(value);
    }
    
    /**
     * Method left package private to allow subclassing for {@code DtnameNm}.
     * 
     * @param value is string value this name represents 
     */
    DtName(String value) {
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
