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
 * Used to store optional INTEGER values (usually NUMBER(5, 0) in Oracle).
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptIntegerAdapter.class)
public class DtOptInteger extends DtOptional<Integer> implements DtOptNumeric {

    private static final DtOptInteger EMPTY = new DtOptInteger();

    static private final Optional<Short> SCALE = Optional.of((short) 0);
    static private final Optional<Integer> MAXPRECISION = Optional.of(10);

    /**
     * Returns an empty {@code DtOptInteger} instance. No value is present for
     * this Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtInteger}
     */
    public static DtOptInteger empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptInteger} with the specified present non-null
     * value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptInteger} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptInteger of(Integer value) {
        Objects.requireNonNull(value);
        return new DtOptInteger(value);
    }

    /**
     * Returns an {@code DtOptInteger} describing the specified value, if
     * non-null, otherwise returns an empty {@code DtOptInteger}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptInteger} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptInteger}
     */
    public static DtOptInteger ofNullable(Integer value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Constructs DtOptInteger from supplied optional integer value.
     *
     * @param value supplied optional integer value
     * @return instance of DtInteger with given value
     */
    public static DtOptInteger of(Optional<Integer> value) {
        Objects.requireNonNull(value);
        if (!value.isPresent()) {
            return EMPTY;
        }
        return of(value.get());
    }

    /**
     * Returns an {@code DtOptInteger} with the specified present non-null
     * value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptInteger} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptInteger of(DtInteger value) {
        Objects.requireNonNull(value);
        return new DtOptInteger(value.getValue());
    }

    /**
     * Returns an {@code DtOptInteger} describing the specified value, if
     * non-null, otherwise returns an empty {@code DtOptInteger}.
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptInteger} with a present value if the specified
     * value is non-null, otherwise an empty {@code DtOptInteger}
     */
    public static DtOptInteger ofNullable(DtInteger value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Returns an {@code DtOptInteger} with value represented by supplied
     * String. returns empty instance if supplied value is null or empty
     *
     * @param value is string containing integer value
     * @return an {@code DtOptInteger} representing value from string
     */
    public static DtOptInteger parseString(String value) {
        if ((value == null) || value.isEmpty()) {
            return EMPTY;
        }
        return of(Integer.parseInt(value));
    }

    /**
     * Register DtOptInteger type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptInteger.class, Types.INTEGER,
                 DtOptInteger::validatePrecision, DtOptInteger::validateScale,
                 DtOptInteger::eligibleForSqlType);
    }

    /**
     * Precision validator for {@code DtOptInteger}.
     *
     * @param precision is precision supplied on column creation
     * @return specified number or 10 whichever is lower, 10 if not specified
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        if (precision.orElse(11) > 10) {
            return MAXPRECISION;
        }
        return precision;
    }

    /**
     * Scale validator for {@code DtOptInteger}.
     *
     * @param scale is scale supplied on column creation
     * @return 0 as integer has scale of zero
     */
    static public Optional<Short> validateScale(
            Optional<Short> scale) {
        return SCALE;
    }

    /**
     * Marks DtInteger as default for integer SQL types.
     *
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 14 if DtOptInteger can be used for column and -1 otherwise
     */
    public static int eligibleForSqlType(int sqlType,
             Optional<Integer> precision, Optional<Short> scale,
             boolean isNullable, String name) {
        if ((sqlType == Types.INTEGER)
                || (sqlType == Types.SMALLINT) || (sqlType == Types.TINYINT)
                || ((scale.map(val -> val <= 0).orElse(false))
                && (precision.map(val -> val <= 9).orElse(false))
                && (sqlType == Types.NUMERIC))) {
            return 14;
        }
        return -1;
    }

    private DtOptInteger() {
        super();
    }

    /**
     * Creates provys integer value from supplied value.
     *
     * @param value - value new object will be initialised to
     */
    private DtOptInteger(Integer value) {
        super(value);
    }

    @Override
    public String toStringValue() {
        return getValue().map(value -> Integer.toString(value)).orElse("");
    }

    @Override
    public String toString() {
        return getValue().map(value -> Integer.toString(value)).orElse("");
    }

    @Override
    public boolean equals(Object secondObject) {
        if (this == secondObject) {
            return true;
        }
        if (secondObject == null) {
            return false;
        }
        if (secondObject instanceof DtOptInteger) {
            return getValue().equals(((DtOptional) secondObject).getValue());
        } else if (isPresent() && (secondObject instanceof DtInteger)) {
            return get() == ((DtInteger) secondObject).getValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return map(val -> val.hashCode()).orElse(0);
    }

    @Override
    public Optional<Double> getDouble() {
        return map(val -> Double.valueOf(val));
    }

}
