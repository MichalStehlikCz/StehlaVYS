/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.call.SqlStatement;
import java.util.List;

/**
 * SqlSelectBuilder class provides means to easily build SQL SELECT statements.
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
public interface SqlSelectBuilder {

    /**
     * Create simple SqlSelectBuilder based on supplied table name and alias.
     * 
     * @param table is table name or expression to be used in stead in FROM
     * clause
     * @param alias is alias to be given to this table
     * @return SqlSelectBuilder object based on supplied table
     */
    public static SqlSelectBuilder getSimpleSelect(String table, String alias) {
        return new SqlSelectBuilderSimple(SqlFromElem.getFromElem(table, alias));
    }

    /**
     * Build SQL code based on SqlSelectBuilder.
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
     * Build select statement (ready for binding) based on SqlSelectBuilder.
     * 
     * @return select statement as ProvysDb SqlStatement
     */
    public SqlStatement getSqlStatement();

    /**
     * Build select statement (executable by ProvysDb) based on SqlSelectBuilder.
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
    public SqlSelectBuilder addColumn(SqlColumn sqlColumn);
    
    /**
     * Get (immutable) list of from elements.
     * 
     * @return list of from elements this select uses
     */
    public List<SqlFromElem> getFromElems();
    
    /**
     * Get FROM element with specified alias.
     * If element is not found, throw exception.
     * 
     * @param alias is alias that is being looked for
     * @return from element with specified alias if found
     */
    public SqlFromElem getFromElemByAlias(String alias);
    
    /**
     * Get FROM element with specified alias if exists.
     * If element is not found, return null
     * 
     * @param alias is alias that is being looked for
     * @return from element with specified alias if found, null otherwise
     */
    public SqlFromElem getFromElemByAliasIfExists(String alias);
    
    /**
     * Add element to from clause.
     * 
     * @param fromElem is table or select to be added to FROM clause
     * @return itself to support chaining
     */
    public SqlSelectBuilder addFromElem(SqlFromElem fromElem);
    
    /**
     * Add additional condition to WHERE clause.
     * 
     * @param whereCond is condition to be added to WHERE clause
     * @return itself to support chaining
     */
    public SqlSelectBuilder addWhereCond(SqlWhereCond whereCond);
}
