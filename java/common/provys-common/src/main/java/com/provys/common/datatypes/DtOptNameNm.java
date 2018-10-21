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
 * Represents optional value of provys domain NAME_NM.
 * Domain is used for internal names of objects and is similar to NAME, with
 * additional restriction on allowed characters
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptNameNmAdapter.class)
public class DtOptNameNm extends DtOptName {

    /**
     * Register {@code DtOptNameNm} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptNameNm.class, Types.VARCHAR
                , DtOptNameNm::validatePrecision
                , DtOptNameNm::eligibleForSqlType);
    }
    
    static private final Optional<Integer> MAXPRECISION = Optional.of(200);
    
    /**
     * Precision validator for {@code DtNameNm}.
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
     * Marks {@code DtNameNm} as default for Strings with length 200 if name of
     * column ends with _NM.
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
                && (precision.map((value) -> value<=200).orElse(false))
                && name.endsWith("_NM")) {
            return 29;
        }
        return -1;
    }

    private static final DtOptNameNm EMPTY = new DtOptNameNm();
    
    protected DtOptNameNm() {
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
     * @return an empty {@code DtNameNm}
     */
    public static DtOptNameNm empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptNameNm} with the specified present non-null value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptName} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptNameNm of(String value) {
        Objects.requireNonNull(value);
        return new DtOptNameNm(value);
    }

    /**
     * Returns an {@code DtOptNameNm} describing the specified value, if
     * non-null, otherwise returns an empty {@code DtOptNameNm}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptNameNm} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptNameNm}
     */
    public static DtOptNameNm ofNullable(String value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Creates DtOptName from supplied value. Verifies, that supplied value is
     * not longer than 200 characters
     *
     * @param value - string value new object will be initialised with
     */
    public DtOptNameNm(String value) {
        super(value);
    }

}
