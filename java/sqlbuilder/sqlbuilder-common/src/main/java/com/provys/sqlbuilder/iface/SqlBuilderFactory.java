/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.BindValue;

/**
 * Creates instances of basic SqlBuilder objects.
 * 
 * @author stehlik
 */
public interface SqlBuilderFactory {

    /**
     * Create simple SqlSelectBuilder based on supplied table name and alias.
     * 
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SqlSelectBuilder object based on supplied table
     */
    public SqlSelectBuilder getSimpleSelect(String table, String alias);

    /**
     * Create simple SqlSelectBuilder based on supplied column, table name
 and alias.
     * 
     * @param column is column to be selected from resulting select expression
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SqlSelectBuilder object based on supplied table
     */
    public SqlSelectBuilder getSimpleSelect(SqlColumn column, String table
            , String alias);
    
    /**
     * Create simple SqlQueryBuilder based on bind value, representing key.
     * 
     * @param bindValue is supplied bind value, to be used for column
     * @return 
     */
    public SqlQueryBuilder getBindQuery(BindValue bindValue);

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
     * Get column, representing single bind variable.
     * 
     * @param bindValue is bind value, 
     * @return SqlColumn representing single bind variable
     */
    public SqlColumn getBindColumn(BindValue bindValue);
    
    /**
     * Get column, representing single bind variable.
     * 
     * @param bindValue is bind value, 
     * @param alias is alias to be assigned to column
     * @return SqlColumn representing single bind variable
     */
    public SqlColumn getBindColumn(BindValue bindValue, String alias);
    
    /**
     * Get simple from element (table or expression with alias, no join).
     * 
     * @param table is name of table or SQL expression enclosed in ()
     * @param alias is alias to be assigned to this table
     * @return from clause representing given table and alias
     */
    public SqlFromElem getFromElem(String table, String alias);
}
