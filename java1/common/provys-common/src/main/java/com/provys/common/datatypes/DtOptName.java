/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import javax.json.bind.annotation.JsonbTypeAdapter;
import java.sql.Types;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents optional value of provys domain NAME. NAME domain is stored as
 * VARCAR2(200) in Oracle database. It is mostly used on properties, that
 * contain descriptive name of an object
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptNameAdapter.class)
public class DtOptName extends DtOptString {

    private static final DtOptName EMPTY = new DtOptName();

    static private final Optional<Integer> MAXPRECISION = Optional.of(200);

    /**
     * Returns an empty {@code Optional} instance. No value is present for this
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
     * @throws DtName.NameTooLongException is value is longer than 200
     * characters
     */
    public static DtOptName of(String value) {
        Objects.requireNonNull(value);
        return new DtOptName(value);
    }

    /**
     * Returns an {@code DtOptName} describing the specified value, if non-null,
     * otherwise returns an empty {@code DtOptName}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptName} with a present value if the specified value
     * is non-null, otherwise an empty {@code DtOptName}
     * @throws DtName.NameTooLongException is value is longer than 200
     * characters
     */
    public static DtOptName ofNullable(String value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Returns an {@code DtOptName} with the specified Optional value.
     *
     * @param value the value to be used, which must be non-null
     * @return an {@code DtOptName} with the value present
     * @throws NullPointerException if value is null
     * @throws DtName.NameTooLongException is value is longer than 200
     * characters
     */
    public static DtOptName of(Optional<String> value) {
        Objects.requireNonNull(value);
        return new DtOptName(value);
    }

    /**
     * Returns an {@code DtOptName} with the specified DtName value.
     *
     * @param value the value to be used, which must be non-null
     * @return an {@code DtOptName} with the value present
     * @throws NullPointerException if value is null
     * @throws DtName.NameTooLongException is value is longer than 200
     * characters
     */
    public static DtOptName of(DtName value) {
        Objects.requireNonNull(value);
        return new DtOptName(value.getValue());
    }

    /**
     * Returns an {@code DtOptName} with the specified DtName value. Returns
     * empty {@code DtOptName} if supplied value is null
     *
     * @param value the value to be used
     * @return an {@code DtOptName} with the value present
     * @throws DtName.NameTooLongException is value is longer than 200
     * characters
     */
    public static DtOptName ofNullable(DtName value) {
        return (value == null) ? EMPTY : new DtOptName(value.getValue());
    }

    /**
     * Register {@code DtOptName} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptName.class, Types.VARCHAR,
                 DtOptName::validatePrecision, DtOptName::eligibleForSqlType);
    }

    /**
     * Precision validator for {@code DtName}.
     *
     * @param precision is precision supplied on column creation
     * @return specified number or 200 whichever is lower, 200 if not specified
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        if (precision.map((value) -> value > 200).orElse(true)) {
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
    public static int eligibleForSqlType(int sqlType,
             Optional<Integer> precision, Optional<Short> scale,
             boolean isNullable, String name) {
        if ((sqlType == Types.VARCHAR)
                && (precision.map((value) -> value <= 200).orElse(false))) {
            return 19;
        }
        return -1;
    }

    /**
     * Creates empty DtOptName instance.
     */
    protected DtOptName() {
        super();
    }

    /**
     * Creates {@code DtOptName} instance with supplied String value
     *
     * @param value is value this object should hold
     */
    protected DtOptName(String value) {
        super(DtName.validateName(value));
    }

    /**
     * Creates {@code DtOptName} instance with supplied {@code Optional<String>}
     * value
     *
     * @param value is value this object should hold
     */
    protected DtOptName(Optional<String> value) {
        super(value);
        value.ifPresent(DtName::validateName);
    }

}
