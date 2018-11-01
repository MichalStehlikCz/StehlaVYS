/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.util.Optional;

/**
 *
 * @author stehlik
 *
 * Used to store PROVYS VARCHAR and NOTE values. Also ancestor for name and
 * name_nm subtypes
 */
abstract public class DtOptString extends DtOptional<String> {

    /**
     * Creates empty provys optional string.
     */
    protected DtOptString() {
        super();
    }

    /**
     * Creates provys string based on supplied value
     *
     * @param value that will be assigned to newly created provys string object
     */
    protected DtOptString(String value) {
        super(value);
    }

    /**
     * Creates provys string based on supplied Optional value
     *
     * @param value that will be assigned to newly created provys string object
     */
    protected DtOptString(Optional<String> value) {
        super(value);
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
        // replace ', newline and CHR(0) characters to prevent SQL injection
        return getValue().map((value) -> "'" + value.replace("'", "''")
                .replace("\n", "'||CHR(10)||'")
                .replace("\0", "\\0") + "'").orElse("NULL");
    }

    @Override
    public boolean equals(Object secondObject) {
        if (this == secondObject) {
            return true;
        }
        if (secondObject instanceof DtOptString) {
            return getValue().equals(((DtOptString) secondObject).getValue());
        }
        if (secondObject instanceof DtString) {
            if (!isPresent()) {
                return false;
            }
            return get().equals(((DtString) secondObject).getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getValue().map((value) -> value.hashCode()).orElse(0);
    }
}
