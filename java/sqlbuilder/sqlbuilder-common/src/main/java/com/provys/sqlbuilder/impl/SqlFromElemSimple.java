/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class represents table or expression placed in FROM clause.
 * It is optional to specify join condition that connects this table with
 * some other FromElem(s).
 * 
 * @author stehlik
 */
class SqlFromElemSimple extends SqlFromElemAncestor {
    
    private final String table;
    private boolean outerJoin = false;
    private final List<SqlColumnJoin> columns;
    
    public SqlFromElemSimple(String table) {
        this.table = table;
        this.columns = new ArrayList<>(0);
    }

    public SqlFromElemSimple(String table, String alias) {
        super(alias);
        this.table = table;
        this.columns = new ArrayList<>(0);
    }

    @Override
    public void buildSql(CodeBuilder code) {
        code.append(getTable());
        appendAlias(code);
        code.appendLine();
    }

    @Override
    public void buildJoinSql(CodeBuilder code) {
        columns.forEach((sqlColumnJoin) -> {
            code.append("(").increaseTempIdent(2);
            sqlColumnJoin.getSource().buildSqlNoNewLine(code, false);
            if (outerJoin) {
                code.append("(+)");
            }
            code.append("=");
            sqlColumnJoin.getTarget().buildSqlNoNewLine(code, false);
            code.removeTempIdent().append(")");
        });
    }

    @Override
    public boolean hasJoinSql() {
        return this.columns.size()>0;
    }

    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @return the outerJoin
     */
    public boolean isOuterJoin() {
        return outerJoin;
    }

    /**
     * @param outerJoin the outerJoin to set
     */
    public void setOuterJoin(boolean outerJoin) {
        this.outerJoin = outerJoin;
    }

    /**
     * @return the columns
     */
    public List<SqlColumnJoin> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(List<SqlColumnJoin> columns) {
        this.columns.clear();
        this.columns.addAll(columns);
    }
}
