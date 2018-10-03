/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.common.error.ProvysException;
import java.util.List;

/**
 * Query builder is special case of SqlBuilder, aimed at building chain of
 * queries. Unlike SelectBuilder, query builder does not support direct
 * manipulation with columns. Instead, it expects to return key in target entity
 * 
 * @author stehlik
 */
public interface QueryBuilder {
    
    /**
     * Build SQL IN code based on QueryBuilder.
     * 
     * @param code - CodeBuilder used to build and then retrieve SQL code.
     * @param columns - columns to be placed in IN clause
     */
    public void buildInSql(CodeBuilder code, List<SqlColumn> columns);

    /**
     * Build SQL EXISTS code based on QueryBuilder.
     * 
     * @param code - CodeBuilder used to build and then retrieve SQL code.
     * @param columns - columns to be added to WHERE clause
     */
    public void buildExistsSql(CodeBuilder code, List<SqlColumn> columns);
    
    /**
     * Checks that Query builder's columns can be used together with supplied
     * columns to build IN or EXISTS query.
     * 
     * @param columns - columns to be compared against QueryBuilder's own set of
     * columns
     */
    public void checkColumns(List<SqlColumn> columns);
    
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
     * Number of columns does not match subquery.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnsNumberMismatchException extends ProvysException {

        private static final long serialVersionUID = 1L;

        public ColumnsNumberMismatchException() {
            super("Number of columns does not match subquery");
        }
    }
}
