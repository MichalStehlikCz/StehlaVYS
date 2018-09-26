/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import java.util.List;

/**
 * SqlBuilder class provides means to easily build SQL SELECT statements.
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
public interface SqlBuilder {

    /**
     * Build SQL code based on SqlBuilder query.
     * 
     * @param code - CodeBuilder used to build and then retrieve SQL code.
     */
    public void buildSql(CodeBuilder code);

    /**
     * Builds SELECT statement for EXISTS clause in where condition.
     * Difference against "normal" buildSql is that columns are not used in
     * SELECT clause, instead, they are pushed to WHERE clause and compared
     * against supplied columns / values
     * 
     * @param code - CodeBuilder used to build and then retrieve SQL code.
     * @param equalColumns - values that will be compared with result columns
     * from query, represented by this SqlBuilder
     */
    public void buildExistsSql(CodeBuilder code, List<SqlColumn> equalColumns);

    /**
     * Evaluate expected cost of this SqlBuilder statement.
     * In general, if data can be accessed using index access, returns 1, if
     * not,returns 1000. Does not use any table statistics, Oracle optimiser
     * is far superior. Probably not that important in newer Oracle versions as
     * Oracle is able to rewrite IN / EXISTS queries and push predicates as
     * needed, limiting need for handling this during query design
     * 
     * @return value representing expected cost of query
     */
    public int getCost();

    /**
     * Get list of columns of given SELECT statement.
     * 
     * @return list of columns
     */
    public List<SqlColumn> getColumns();
    
}
