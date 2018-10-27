/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.provysdb.call.ColumnDef;
import java.util.Collections;
import java.util.List;

/**
 * Represents column in select query.
 * 
 * @author stehlik
 */
public interface SqlColumn {
    
    /**
     * Build SQL code for column (full line).
     * 
     * @param code is CodeBuilder used to build SQL statement
     * @param addAliasClause defines if alias clause should be placed after
     * column code
     */
    public void buildSql(CodeBuilder code, boolean addAliasClause);
    
    /**
     * Build SQL code for column; if possible, build it in line.
     * Note that it is perfectly legal if code is split to multiple lines,
     * for example if column is complete SQL statement. But if possible (e.g.
     * in case it is simple column), it is placed on single line
     * 
     * @param code is CodeBuilder used to build SQL statement
     * @param addAliasClause defines if alias clause should be placed after
     * column code
     */
    public void buildSqlNoNewLine(CodeBuilder code
            , boolean addAliasClause);

    /**
     * Translates SqlColumn to ColumnDef, used by SqlCall.
     * 
     * @return ColumnDef describing given column, null if ColumnDef cannot be
     * constructed from SqlColumn
     */
    public ColumnDef getColumnDef();
   
    /**
     * @return the alias
     */
    public String getAlias();

    /**
     * @return the indexed flag
     */
    public boolean isIndexed();
    
    /**
     * Retrieve list of from elements this column depends on.
     * In default implementation, returns empty list.
     * 
     * @return list of elements this column is connected to
     */
    public default List<SqlFromElem> getFromElems() {
        return Collections.EMPTY_LIST;
    }

}
