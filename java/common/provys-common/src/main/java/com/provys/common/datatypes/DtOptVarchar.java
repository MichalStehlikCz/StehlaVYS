/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.sql.Types;
import java.util.Objects;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Represents optional value of provys string.
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptVarcharAdapter.class)
public class DtOptVarchar extends DtOptString {

    /**
     * Register {@code DtOptVarchar} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptVarchar.class, Types.VARCHAR
                , DtOptVarchar::validatePrecision
                , DtOptVarchar::eligibleForSqlType);
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
     * Marks {@code DtOptVarchar} as default for string SQL types.
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
        if (((sqlType == Types.CHAR)
                || (sqlType == Types.NCHAR) || (sqlType == Types.NVARCHAR)
                || (sqlType == Types.VARCHAR))) {
            return 9;
        }
        return -1;
    }

    private static final DtOptVarchar EMPTY = new DtOptVarchar();
    
    protected DtOptVarchar() {
        super();
    }

    /**
     * Returns an empty {@code DtOptVarchar} instance.  No value is present for
     * this Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code DtOptVarchar.empty()}. There is no guarantee that it is
     * a singleton. Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtOptVarchar}
     */
    public static DtOptVarchar empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptVarchar} with the specified present non-null
     * value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptVarcar} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptVarchar of(String value) {
        Objects.requireNonNull(value);
        return new DtOptVarchar(value);
    }

    /**
     * Returns an {@code DtOptVarchar} describing the specified value, if
     * non-null, otherwise returns an empty {@code DtOptVarchar}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptVarchar} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptVarchar}
     */
    public static DtOptVarchar ofNullable(String value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Creates DtOptVarchar from supplied value.
     *
     * @param value - string value new object will be initialised with
     */
    public DtOptVarchar(String value) {
        super(value);
    }

}
