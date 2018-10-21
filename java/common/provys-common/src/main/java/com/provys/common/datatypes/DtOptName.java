/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;
import java.sql.Types;
import java.util.Objects;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Represents optional value of provys domain NAME.
 * NAME domain is stored as VARCAR2(200) in Oracle database. It is mostly
 * used on properties, that contain descriptive name of an object
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptNameAdapter.class)
public class DtOptName extends DtOptString {

    /**
     * Register {@code DtOptName} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptName.class, Types.VARCHAR
                , DtOptName::validatePrecision, DtOptName::eligibleForSqlType);
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
        if (precision.map((value) -> value>200).orElse(true)) {
            return MAXPRECISION;
        }
        return precision;
    }
        
    /**
     * Marks {@code DtName} as default for Strings with length 200.
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
        if ((sqlType == Types.VARCHAR)
                && (precision.map((value) -> value<=200).orElse(false))) {
            return 19;
        }
        return -1;
    }

    private static final DtOptName EMPTY = new DtOptName();
    
    protected DtOptName() {
        super();
    }

    /**
     * Returns an empty {@code Optional} instance.  No value is present for this
     * Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtName}
     */
    public static DtOptName empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptName} with the specified present non-null value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptName} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptName of(String value) {
        Objects.requireNonNull(value);
        return new DtOptName(value);
    }

    /**
     * Returns an {@code DtOptName} describing the specified value, if
     * non-null, otherwise returns an empty {@code DtOptName}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptName} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptName}
     */
    public static DtOptName ofNullable(String value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Creates DtOptName from supplied value. Verifies, that supplied value is
     * not longer than 200 characters
     *
     * @param value - string value new object will be initialised with
     */
    public DtOptName(String value) {
        super(value);
        if ((value != null) && (value.length() > 200)) {
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
