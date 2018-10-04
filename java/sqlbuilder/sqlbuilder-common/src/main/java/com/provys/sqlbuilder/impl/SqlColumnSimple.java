/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;

/**
 * Represents simple column, specified as text, without alias.
 * 
 * @author stehlik
 */
public class SqlColumnSimple extends SqlColumnAncestor {
    
    final String column; // text representing table column
    
    
    public SqlColumnSimple(String column) {
        this.column = column;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        code.append(column);
        if (addAliasClause) {
            appendAlias(code);
        }
    }

}
