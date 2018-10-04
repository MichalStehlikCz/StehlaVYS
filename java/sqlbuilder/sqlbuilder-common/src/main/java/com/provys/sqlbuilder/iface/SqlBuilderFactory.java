/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.common.datatypes.Dt;

/**
 * Creates instances of basic SqlBuilder objects.
 * 
 * @author stehlik
 */
public interface SqlBuilderFactory {

    /**
     * Create simple SelectBuilder based on supplied table name and alias.
     * 
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SelectBuilder object based on supplied table
     */
    public SelectBuilder getSimpleSelect(String table, String alias);

    /**
     * Create simple SelectBuilder based on supplied column, table name
     * and alias.
     * 
     * @param column is column to be selected from resulting select expression
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SelectBuilder object based on supplied table
     */
    public SelectBuilder getSimpleSelect(SqlColumn column, String table
            , String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(Dt value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(Dt value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(String value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(String value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(int value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(int value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(float value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(float value, String alias);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @return column representing given value
     */
    public SqlColumn getValue(double value);
    
    /**
     * Get column, representing supplied (constant) value.
     * 
     * @param value is value, that will be used as literal in expression
     * @param alias is alias to be assigned to column
     * @return column representing given value
     */
    public SqlColumn getValue(double value, String alias);
    
    /**
     * Get simple from element (table or expression with alias, no join).
     * 
     * @param table is name of table or SQL expression enclosed in ()
     * @param alias is alias to be assigned to this table
     * @return from clause representing given table and alias
     */
    public FromElem getFromElem(String table, String alias);
}
