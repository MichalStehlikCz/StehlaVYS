/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.provysdb.call.SqlCall;
import java.util.List;

/**
 * SelectBuilder class provides means to easily build SQL SELECT statements.
 * SqlBuilder allows to build queries both using native SQL and to exploit
 * PROVYS metadata catalogue. Latter is reason why 3rd party library cannot be
 * reasonably utilised - as no 3rd part library has link to catalogue module.
 *  
 * Whole concept works as follows
 * - SQLBuilder represents Entity + key, where key might be reference to
 * column in table or bind variable
 * - it is possible to attach condition to SQLBuilder
 * - it is possible to use SQLBuilder + relation as condition in SQLBuilder
 * - it is possible to attach additional column to SQLBuilder
 * - it is possible to retrieve select statement, list of binds and columns from
 * SQLBuilder
 * 
 * @author stehlik
 */
public interface SelectBuilder {

    /**
     * Build SQL code based on SelectBuilder.
     * 
     * @param code - CodeBuilder used to build and then retrieve SQL code.
     */
    public void buildSql(CodeBuilder code);

    /**
     * Get list of columns of given SELECT statement.
     * 
     * @return list of columns
     */
    public List<SqlColumn> getColumns();
    
    /**
     * Build select statement (executable by ProvysDb) based on SelectBuilder.
     * 
     * @return select statement as ProvysDb SqlCall
     */
    public SqlCall getSqlCall();

    /**
     * Add column to SELECT statement.
     * 
     * @param sqlColumn is column to be added
     * @return itself to support chaining
     */
    public SelectBuilder addColumn(SqlColumn sqlColumn);
    
    /**
     * Add element to from clause.
     * 
     * @param fromElem is table or select to be added to FROM clause
     * @return itself to support chaining
     */
    public SelectBuilder addFromElem(FromElem fromElem);
    
    /**
     * Add additional condition to WHERE clause.
     * 
     * @param whereCond is condition to be added to WHERE clause
     * @return itself to support chaining
     */
    public SelectBuilder addWhereCond(WhereCond whereCond);
}
