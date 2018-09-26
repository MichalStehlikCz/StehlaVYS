/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;

/**
 * Represents simple column, specified as text, without alias.
 * 
 * @author stehlik
 */
public class SqlColumnSimple implements SqlColumn {
    
    final String column;
    
    public SqlColumnSimple(String column) {
        this.column = column;
    }

    @Override
    public void buildSql(CodeBuilder code) {
        code.appendLine(column);
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code) {
        code.append(column);
    }
}
