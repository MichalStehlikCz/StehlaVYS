/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.sql.Types;
import java.util.Optional;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 * Represents PROVYS internal name (domain NAME_NM).
 * Similar to name, with potential restriction regarding allowed characters.
 * 
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbDtNameNmAdapter.class)
public class DtNameNm extends DtName{

    /**
     * Register DtName type to Dt types repository.
     */
    static void register() {
        DtRepository.registerDtType(DtNameNm.class, Types.VARCHAR
                , DtNameNm::validatePrecision, DtNameNm::eligibleForSqlType);
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
    public static int eligibleForSqlType(int sqlType
            , Optional<Integer> precision, Optional<Short> scale
            , boolean isNullable, String name) {
        if ((!isNullable) && (sqlType == Types.VARCHAR)
                && (precision.map((value) -> value<=200).orElse(false))
                && (name.endsWith("_NM"))) {
            return 30;
        }
        return -1;
    }

    /**
     * Creates new DtNameNm object from supplied value. Verifies that supplied
     * value is valid as internal name
     * @param value - val new internal name will be set to
     */
    public DtNameNm(String value){
        super(value);
    }
}
