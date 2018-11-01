/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

/**
 * DtNumeric represents common ancestor for numeric PROVYS types. Adds method
 * for value retrieval as double. Also provides default implementation for
 * toSqlLiteral method
 *
 * @author stehlik
 */
public interface DtNumeric extends Dt {

    @Override
    default public String toSqlLiteral() {
        return this.toStringValue();
    }

    /**
     * Retrieves value of PROVYS number object, converted to double.
     *
     * @return effective value of PROVYS numeric type, converted to double
     */
    abstract public double getDouble();
}
