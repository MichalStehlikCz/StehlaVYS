/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlbuilder;

import com.provys.provysdb.call.BindValue;
import java.util.List;

/**
 * Represents WHERE condition in SQL builder.
 * Different implementations allow to create WHERE conditions based on
 * supplied string, application of unary or binary operator on attribute
 * or on supplied subquery
 * 
 * @author stehlik
 */
public interface WhereCond {
    /**
     * Returns String expression that should be used in WHERE clause of the
     * SQL query.
     * Resulting string can be added to SQL statement. It can be combined with
     * other conditions, connecting it using AND; if built statement contains
     * conditions connected using OR, it is surrounded by parentheses to allow
     * combination using AND with other conditions
     * 
     * @param alias represents alias associated with table
     * @param binds used to add binds to list of binds associated with statement
     * @return String representing WHERE clause representing this condition
     */
    public String getWhere(String alias, List<BindValue> binds);
    
    /**
     * Returns estimated cost of generated condition.
     * Cost estimation is much worse than that provided by Oracle optimiser, but
     * can be used to choose optimal JOIN condition wen navigating to additional
     * tables
     * - 1 - indexed access
     * - 1000 - non-indexed access
     * 
     * @return estimated cost of statement if it is used as primary condition 
     */
    public int getCost();
    
}
