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
 * Represents PROVYS internal name (domain NAME_NM). Similar to name, with
 * potential restriction regarding allowed characters.
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtNameNmAdapter.class)
public class DtNameNm extends DtName {

    static private final Optional<Integer> MAXPRECISION = Optional.of(200);

    /**
     * Creates new DtNameNm object from supplied value. Verifies that supplied
     * value is valid as internal name
     *
     * @param value - val new internal name will be set to
     * @return new {@code DtNameNm} object representing the value
     * @throws NullValueNotSupportedException when supplied value is null or
     * empty String
     * @throws NameTooLongException when supplied value is longer than 200
     * characters
     */
    public static DtNameNm of(String value) {
        return new DtNameNm(value);
    }

    /**
     * Validates NameNm value. Note that unlike for DtName where validation is
     * enforced, in case of DtNameNm, validation must be performed by explicit
     * invocation of validation method as it cannot be performed without
     * knowledge of modifier (that corresponds to subdomain in PROVYS metadata
     * catalogue), extending allowed characters in particular internal name
     * field
     *
     * @param value is value to be validated
     * @param subdomain corresponds to attribute's subdomain, extends characters
     * allowed in given field
     */
    static public void validateNameNm(String value, String subdomain) {
        if (value != null) {
            if (!value.matches("[a-zA-Z][a-zA-Z0-9_$" + subdomain + "]*")) {
                throw new InvalidNameNmValueException(value, subdomain);
            }
        }
    }

    /**
     * Register DtName type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtNameNm.class, Types.VARCHAR,
                 DtNameNm::validatePrecision, DtNameNm::eligibleForSqlType);
    }

    /**
     * Precision validator for {@code DtNameNm}.
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
     * Marks {@code DtNameNm} as default for Strings with length 200 if column
     * name ends with _NM.
     *
     * @param sqlType is value corresponding to SQL type as defined in
     * {@code java.sql.Types}
     * @param precision represents column precision - number of characters for
     * string column, number of digits for numeric column
     * @param scale represents number of digits to right of decimal point for
     * numeric column
     * @param isNullable is flag indicating if column is nullable
     * @param name is name of column
     * @return 30 if {@code DtNameNm} should be used for column and -1 otherwise
     */
    public static int eligibleForSqlType(int sqlType,
             Optional<Integer> precision, Optional<Short> scale,
             boolean isNullable, String name) {
        if ((!isNullable) && (sqlType == Types.VARCHAR)
                && (precision.map((value) -> value <= 200).orElse(false))
                && (name.endsWith("_NM"))) {
            return 30;
        }
        return -1;
    }

    private DtNameNm(String value) {
        super(value);
    }

    /**
     * Exception raised when validated value does no match required format
     */
    @SuppressWarnings("PublicInnerClass")
    static public class InvalidNameNmValueException extends ProvysException {

        InvalidNameNmValueException(String value, String subdomain) {
            super("Invalid NameNm value " + value + "(subdomain "
                    + subdomain + ")");
        }
    }

}
