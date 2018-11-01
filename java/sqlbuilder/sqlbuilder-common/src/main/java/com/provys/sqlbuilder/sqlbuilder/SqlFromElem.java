/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

/**
 * SqlFromElem represents source table in SqlBuilder statement.
 * Source table is kept together with its associated join condition.
 * 
 * @author stehlik
 */
public interface SqlFromElem {

    /**
     * Get simple from element based on table or view, no join.
     * 
     * @param table is name of table or view
     * @param alias is alias to be assigned to this table
     * @return from clause representing given table and alias
     */
    public static SqlFromElem ofTable(String table, String alias) {
        return SqlFromElemSimple.ofTable(table, alias);
    }

    /**
     * Get simple from element based on SELECT expression.
     * Expression should be SELECT statement and will be enclosed in brackets.
     * 
     * @param sqlExpression is SELECT expression that will be used as FROM
     * element, enclosed in brackets
     * @param alias is alias to be assigned to this table
     * @return from clause representing given table and alias
     */
    public static SqlFromElem ofExpression(String sqlExpression, String alias) {
        return SqlFromElemSimple.ofExpression(sqlExpression, alias);
    }

    /**
     * Add from clause, associated with this element to supplied CodeBuilder.
     * 
     * @param code is CodeBuilder object used to build SQL statement
     */
    public void buildSql(CodeBuilder code);
    
    /**
     * Add join clause associated with this element to supplied CodeBuilder.
     * Based on PROVYS conventions, join clause is added to WHERE section of
     * SELECT statement, not in FROM section
     * 
     * @param code is CodeBuilder used to build SQL statement
     */
    public void buildJoinSql(CodeBuilder code);
    
    /**
     * Method indicates if this from element will produce join statement to
     * WHERE clause.
     * 
     * @return whether from element will add join clause to WHERE clause
     */
    public boolean hasJoinSql();
    
    /**
     * Retrieve alias valid for give WHERE element.
     * Even though alias is option in general, based on PROVYS conventions, it
     * should always be used
     * 
     * @return alias used for given statement
     */
    public String getAlias();
}
