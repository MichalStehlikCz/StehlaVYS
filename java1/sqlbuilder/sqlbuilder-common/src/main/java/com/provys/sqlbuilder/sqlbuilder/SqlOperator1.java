/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.error.ProvysException;

/**
 * Enumerates available SQL single operand operators.
 * 
 * @author stehlik
 */
public enum SqlOperator1 {
     NULL("IS NULL", "IS NOT NULL");

    static SqlOperator2 getByOperator(String operator) {
        for (SqlOperator2 sqlOperator : SqlOperator2.values()) {
            if (operator.equalsIgnoreCase(sqlOperator.getOperator())) {
                return sqlOperator;
            }
        }
        throw new OperatorNotFoundException(operator);
    }

    private final String operator;
    private final String negOperator;
    
    SqlOperator1(String operator, String negOperator) {
        this.operator = operator;
        this.negOperator = negOperator;
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @return the negOperator
     */
    public String getNegOperator() {
        return negOperator;
    }

    /**
     * Raised when attempt to find operator using supplied string fails.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class OperatorNotFoundException extends ProvysException {

        private static final long serialVersionUID = 1L;
        
        OperatorNotFoundException(String operator) {
            super("SqlOperator not found using supplied string " + operator);
        }
    }
    
}

