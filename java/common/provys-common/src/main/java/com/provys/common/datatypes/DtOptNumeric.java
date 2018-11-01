/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.util.Optional;

/**
 * DtOptNumeric represents common ancestor for optional numeric PROVYS types. It
 * adds method for value retrieval as Optional/Double
 *
 * @author stehlik
 */
public interface DtOptNumeric extends Dt {

    public boolean isPresent();

    @Override
    default public String toSqlLiteral() {
        if (!this.isPresent()) {
            return "TO_NUMBER(NULL)";
        }
        return this.toStringValue();
    }

    /**
     * returns value of PROVYS number object, converted to double
     *
     * @return effective value of PROVYS numeric type, converted to double
     */
    abstract public Optional<Double> getDouble();
}
