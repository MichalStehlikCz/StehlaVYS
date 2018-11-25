/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import com.provys.common.error.ProvysException;

import javax.json.bind.annotation.JsonbTypeAdapter;
import java.sql.Types;
import java.util.Optional;

/**
 * Representation of PROVYS UID values.
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtUidAdapter.class)
public class DtUid implements Dt {

    static private final Optional<Integer> PRECISION = Optional.of(38);
    static private final Optional<Short> SCALE = Optional.of((short) 0);

    /**
     * Creates provys UID value from supplied string. System validates data
     * format, but does not validate if given value actually represents existing
     * object. Uses {@code validateUid} method for validation
     *
     * @param value represents value this object will be assigned
     * @return {@code DtUid} object representing supplied value
     * @throws NullValueNotSupportedException when null value or empty string is
     * passed
     */
    public static DtUid of(String value) {
        return new DtUid(value);
    }

    /**
     * Checks if supplied value is valid UID value.
     *
     * @param value is value to be checked as valid Uid
     * @return source value to allow validation on the first use
     * @throws UidTooLongException id supplied ID value is longer than 38
     * characters
     * @throws UidNotNumberException when supplied value is not a number
     */
    public static String validateUid(String value) {
        if (value.length() > 38) {
            throw new UidTooLongException(value);
        }
        if (!value.matches("\\d+")) {
            throw new UidNotNumberException(value);
        }
        return value;
    }

    /**
     * Register {@code DtUid} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtUid.class, Types.VARCHAR,
                 DtUid::validatePrecision, DtUid::validateScale,
                 DtUid::eligibleForSqlType);
    }

    /**
     * Precision validator for {@code DtUid}.
     *
     * @param precision is precision supplied on column creation
     * @return 38 as it is fixed precision of PROVYS Uid
     */
    static public Optional<Integer> validatePrecision(
            Optional<Integer> precision) {
        return PRECISION;
    }

    /**
     * Scale validator for {@code DtUid}.
     *
     * @param scale is scale supplied on column creation
     * @return 0 as UID is whole number
     */
    static public Optional<Short> validateScale(
            Optional<Short> scale) {
        return SCALE;
    }

    /**
     * Marks {@code DtUid} as default for Numeric columns if column name ends
     * with _ID or _RF.
     *
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 30 if {@code DtUid} should be used for column and -1 otherwise
     */
    public static int eligibleForSqlType(int sqlType,
             Optional<Integer> precision, Optional<Short> scale,
             boolean isNullable, String name) {
        if ((!isNullable) && (sqlType == Types.NUMERIC)
                && ((name.endsWith("_ID"))
                || (name.endsWith("_RF")))) {
            return 30;
        }
        return -1;
    }

    private final String value;

    private DtUid(String value) {
        if ((value == null) || (value.isEmpty())) {
            throw new Dt.NullValueNotSupportedException();
        }
        this.value = validateUid(value);
    }

    /**
     * Getter method for value - internal String representation of UID value.
     *
     * @return String value corresponding to this UID
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toStringValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String toSqlLiteral() {
        return this.value;
    }

    @Override
    public boolean equals(Object secondObject) {
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtUid) {
            return getValue().equals(((DtUid) secondObject).getValue());
        }
        if (secondObject instanceof DtOptUid) {
            return ((DtOptUid) secondObject).map(val -> val.equals(getValue()))
                    .orElse(false);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    /**
     * Exception indicating that supplied value is not valid PROVYS Uid, because
     * it cannot be longer than 38 digits
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UidTooLongException extends ProvysException {

        private static final long serialVersionUID = 1L;

        /**
         * Creates exception and embeds supplied value to error message
         *
         * @param value is offending value that is too long
         */
        public UidTooLongException(String value) {
            super("Value supplied to Uid constructor is too long (" + value + ")");
        }
    }

    /**
     * Exception indicating that supplied value is not valid PROVYS Uid, because
     * it contains non/numeric characters
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UidNotNumberException extends ProvysException {

        private static final long serialVersionUID = 1L;

        /**
         * Creates exception and embeds supplied value to error message
         *
         * @param value is offending value that is non/numeric
         */
        public UidNotNumberException(String value) {
            super("Value supplied to Uid constructor is not a number (" + value + ")");
        }
    }
}
