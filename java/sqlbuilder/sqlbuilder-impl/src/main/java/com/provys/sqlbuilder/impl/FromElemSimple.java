/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasJoinSql() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        this.joinColumns.add(joinColumn);
    }

    /**
     * @param joinColumns the joinColumns to set
     */
    public void setJoinColumns(List<SqlColumn> joinColumns) {
        this.joinColumns.clear();
        joinColumns.forEach((joinColumn) -> addJoinColumn(joinColumn));
    }
    
}
