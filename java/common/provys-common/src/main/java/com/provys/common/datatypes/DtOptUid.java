package com.provys.common.datatypes;

import java.sql.Types;
import java.util.Objects;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Representation of optional PROVYS UID values.
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtOptUidAdapter.class)
public class DtOptUid extends DtOptional<String> {

    static private final Optional<Integer> PRECISION = Optional.of(38);
    static private final Optional<Short> SCALE = Optional.of((short) 0);
    private static final DtOptUid EMPTY = new DtOptUid();

    /**
     * Returns an empty {@code DtOptUid} instance. No value is present for this
     * Optional.
     *
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     *
     * @return an empty {@code DtOptUid}
     */
    public static DtOptUid empty() {
        return EMPTY;
    }

    /**
     * Returns an {@code DtOptUid} with the specified present non-null value.
     * Uses {@code DtUid.validateUid} for validation of supplied value
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptUid} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptUid of(String value) {
        Objects.requireNonNull(value);
        return new DtOptUid(value);
    }

    /**
     * Returns an {@code DtOptUid} describing the specified value, if non-null,
     * otherwise returns an empty {@code DtOptUid}. Uses
     * {@code DtUid.validateUid} for validation of supplied value
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptUid} with a present value if the specified value
     * is non-null, otherwise an empty {@code DtOptUid}
     */
    public static DtOptUid ofNullable(String value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Returns an {@code DtOptUid} with the specified value. Uses
     * {@code DtUid.validateUid} for validation of supplied value
     *
     * @param value the value to be present
     * @return an {@code DtOptUid} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptUid of(Optional<String> value) {
        Objects.requireNonNull(value);
        return new DtOptUid(value);
    }

    /**
     * Returns an {@code DtOptUid} with the specified present non-null value.
     *
     * @param value the value to be present, which must be non-null
     * @return an {@code DtOptUid} with the value present
     * @throws NullPointerException if value is null
     */
    public static DtOptUid of(DtUid value) {
        Objects.requireNonNull(value);
        return new DtOptUid(value.getValue());
    }

    /**
     * Returns an {@code DtOptUid} describing the specified value, if non-null,
     * otherwise returns an empty {@code DtOptUid}. Uses
     * {@code DtUid.validateUid} for validation of supplied value
     *
     * @param value the possibly-null value to describe
     * @return an {@code DtOptUid} with a present value if the specified value
     * is non-null, otherwise an empty {@code DtOptUid}
     */
    public static DtOptUid ofNullable(DtUid value) {
        return value == null ? empty() : of(value);
    }

    /**
     * Register {@code DtUid} type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtOptUid.class, Types.VARCHAR,
                 DtOptUid::validatePrecision, DtOptUid::validateScale,
                 DtOptUid::eligibleForSqlType);
    }

    /**
     * Precision validator for {@code DtOptUid}.
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
     * Marks {@code DtOptUid} as default for Numeric columns if column name ends
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
        if ((sqlType == Types.NUMERIC)
                && ((name.endsWith("_ID"))
                || (name.endsWith("_RF")))) {
            return 29;
        }
        return -1;
    }

    private DtOptUid() {
        super();
    }

    private DtOptUid(String value) {
        super(DtUid.validateUid(value));
    }

    private DtOptUid(Optional<String> value) {
        super(value);
        value.ifPresent(DtUid::validateUid);
    }

    @Override
    public String toStringValue() {
        return getValue().orElse("");
    }

    @Override
    public String toString() {
        return getValue().orElse("");
    }

    @Override
    public String toSqlLiteral() {
        return getValue().orElse("TO_NUMBER(NULL)");
    }

    @Override
    public boolean equals(Object secondObject) {
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtOptUid) {
            return getValue().equals(((DtOptional) secondObject).getValue());
        }
        if ((isPresent()) && (secondObject instanceof DtUid)) {
            return get().equals(((DtUid) secondObject).getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return map(val -> val.hashCode()).orElse(0);
    }
}
