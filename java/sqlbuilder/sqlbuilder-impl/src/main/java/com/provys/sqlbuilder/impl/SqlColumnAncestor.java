/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;

/**
 * Represents column in select query.
 * 
 * @author stehlik
 */
abstract public class SqlColumnAncestor implements SqlColumn {
    
    private String alias;
    
    public SqlColumnAncestor() {
    }
    
    public SqlColumnAncestor(String alias) {
        this.alias = alias;
    }
    
    @Override
    public void buildSql(CodeBuilder code, boolean addAliasClause) {
        buildSqlNoNewLine(code, addAliasClause);
        code.appendLine();
    }
    
    protected void appendAlias(CodeBuilder code) {
        if (getAlias() != null) {
            code.append(" ").append(getAlias());
        }
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

}