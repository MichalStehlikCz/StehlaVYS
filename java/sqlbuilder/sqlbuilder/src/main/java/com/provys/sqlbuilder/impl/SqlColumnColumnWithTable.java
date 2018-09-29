/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.FromElem;

/**
 * Implements simple column, with alias specified as referenced fromElem.
 * Expects that column is single column from fromElem table and places alias
 * from fromElem in front of column - position for alias is not indicated in
 * column text.
 * 
 * @author stehlik
 */
public class SqlColumnColumnWithTable extends SqlColumnSimple {
    
    final FromElem fromElem;
    
    public SqlColumnColumnWithTable(String column, FromElem fromElem) {
        super(column);
        this.fromElem = fromElem;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        if (fromElem.getAlias() != null) {
            code.append(fromElem.getAlias() + ".");
        }
        code.append(column);
        if (addAliasClause) {
            appendAlias(code);
        }
    }
}
