/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

/**
 * Represents WHERE condition in SQL builder.
 * Different implementations allow to create WHERE conditions based on
 * supplied string, application of unary or binary operator on attribute
 * or on supplied subquery
 * 
 * @author stehlik
 */
public interface SqlWhereCond {
    /**
     * Adds expression that should be used in WHERE clause of the SQL query.
     * Condition can be combined with other conditions, connecting it using AND;
     * if built statement contains conditions connected using OR, it is
     * surrounded by parentheses to allow combination using AND with other
     * conditions
     * 
     * @param code is CodeBuilder used to build resulting statement
     */
    public void buildWhere(CodeBuilder code);
    
    /**
     * Returns estimated cost of generated condition.
     * Cost estimation is much worse than that provided by Oracle optimiser, but
     * can be used to choose optimal JOIN condition when navigating to
     * additional tables
     * - 1 - indexed access
     * - 1000 - non-indexed access
     * 
     * @return estimated cost of statement if it is used as primary condition 
     */
    public double getCost();
    
    /**
     * Reports if where condition is empty and can be ignored.
     * 
     * @return true if where condition is empty, false if it will generate some
     * non-trivial code
     */
    public boolean isEmpty();
}
