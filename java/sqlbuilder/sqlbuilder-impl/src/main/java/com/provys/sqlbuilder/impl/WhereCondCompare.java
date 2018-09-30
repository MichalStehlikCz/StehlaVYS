/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlOperator;
import com.provys.sqlbuilder.iface.WhereCond;

/**
 * Builds condition, based on two columns (might be constants) and operator.
 * 
 * @author stehlik
 */
public class WhereCondCompare implements WhereCond {
    
    private boolean negative = false;
    private SqlColumn column1;
    private SqlColumn column2;
    private SqlOperator operator;
    
    public WhereCondCompare() {
    }
    
    public WhereCondCompare(SqlColumn column1, SqlColumn column2
            , SqlOperator operator) {
        this.column1 = column1;
        this.column2 = column2;
        this.operator = operator;
    }

    public WhereCondCompare(SqlColumn column1, SqlColumn column2
            , SqlOperator operator, boolean negative) {
        this.column1 = column1;
        this.column2 = column2;
        this.operator = operator;
        this.negative = negative;
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        if (this.negative) {
            code.append("NOT ");
        }
        code.append("(");
        column1.buildSqlNoNewLine(code, false);
        code.append(operator.getOperator());
        column2.buildSqlNoNewLine(code, false);
        code.appendLine(")");
    }

    @Override
    public int getCost() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
