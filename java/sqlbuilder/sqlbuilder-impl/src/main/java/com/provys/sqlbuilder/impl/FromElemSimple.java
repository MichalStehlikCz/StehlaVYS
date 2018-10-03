/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class represents table or expression placed in FROM clause.
 * It is optional to specify join condition that connects this table with
 * some other FromElem(s).
 * 
 * @author stehlik
 */
public class FromElemSimple extends FromElemAncestor {
    
    private String table;
    private boolean outerJoin = false;
    private final List<SqlColumn> joinTos;
    private final List<SqlColumn> joinColumns;
    
    public FromElemSimple() {
        joinTos = new ArrayList<>(1);
        joinColumns = new ArrayList<>(1);
    }

    @Override
    public void buildSql(CodeBuilder code) {
        code.append(getTable());
        appendAlias(code);
        code.appendLine();
    }

    @Override
    public void buildJoinSql(CodeBuilder code) {
        checkColumns();
        Iterator<SqlColumn> joinColumnIterator = this.joinColumns.iterator();
        Iterator<SqlColumn> joinToIterator = this.joinTos.iterator();
        while (joinColumnIterator.hasNext()) {
            SqlColumn joinColumn = joinColumnIterator.next();
            SqlColumn joinTo = joinToIterator.next();
            code.append("(").increaseTempIdent(2);
            joinColumn.buildSqlNoNewLine(code, false);
            code.append("=");
            joinTo.buildSqlNoNewLine(code, false);
            code.removeTempIdent().append(")");
        }
    }

    @Override
    public boolean hasJoinSql() {
        return this.joinColumns.size()>0;
    }

    /**
     * @return the table
     */
    public String getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(String table) {
        this.table = table;
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
     * @return the joinTos
     */
    public List<SqlColumn> getJoinTos() {
        return Collections.unmodifiableList(joinTos);
    }

    /**
     * @param joinTos the joinTos to set
     */
    public void setJoinTos(List<SqlColumn> joinTos) {
        this.joinTos.clear();
        this.joinTos.addAll(joinTos);
    }

    /**
     * @return the joinColumns
     */
    public List<SqlColumn> getJoinColumns() {
        return Collections.unmodifiableList(joinColumns);
    }

    public void addJoinColumn(SqlColumn joinColumn) {
        this.getJoinColumns().add(joinColumn);
    }

    /**
     * @param joinColumns the joinColumns to set
     */
    public void setJoinColumns(List<SqlColumn> joinColumns) {
        this.joinColumns.clear();
        joinColumns.forEach((joinColumn) -> addJoinColumn(joinColumn));
    }
    
    /**
     * Checks if condition can be build from columns.
     * Pre-condition is that both column sets are empty or both contain same
     * number of elements.
     */
    private void checkColumns() {
        if (this.joinColumns.size() != this.joinTos.size()) {
            
        }
    }

    /**
     * Column number mismatch.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnNumberMismatchException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ColumnNumberMismatchException() {
            super("Numbers of join columns and join to columns do not match");
        }
    }
    
}
