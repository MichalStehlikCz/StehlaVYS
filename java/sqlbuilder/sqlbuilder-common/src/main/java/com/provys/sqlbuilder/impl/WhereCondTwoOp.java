/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlOperator2;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlWhereCond;

/**
 * Builds condition, based on two columns (might be constants) and operator.
 * 
 * @author stehlik
 */
class WhereCondTwoOp implements SqlWhereCond {
    
    private boolean negative = false;
    private SqlColumn column1;
    private SqlColumn column2;
    private SqlOperator2 operator;
    
    public WhereCondTwoOp() {
    }
    
    public WhereCondTwoOp(SqlColumn column1, SqlOperator2 operator
            , SqlColumn column2) {
        this.column1 = column1;
        this.column2 = column2;
        this.operator = operator;
    }

    public WhereCondTwoOp(SqlColumn column1, SqlOperator2 operator
            , SqlColumn column2, boolean negative) {
        this.column1 = column1;
        this.column2 = column2;
        this.operator = operator;
        this.negative = negative;
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        code.append("(");
        getColumn1().buildSqlNoNewLine(code, false);
        code.append(this.isNegative()?getOperator().getNegOperator():getOperator().getOperator());
        getColumn2().buildSqlNoNewLine(code, false);
        code.appendLine(")");
    }

    @Override
    public double getCost() {
        return 1000; // we have no idea if columns are indexed
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * @return the negative
     */
    public boolean isNegative() {
        return negative;
    }

    /**
     * @param negative the negative to set
     */
    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    /**
     * @return the column1
     */
    public SqlColumn getColumn1() {
        return column1;
    }

    /**
     * @param column1 the column1 to set
     */
    public void setColumn1(SqlColumn column1) {
        this.column1 = column1;
    }

    /**
     * @return the column2
     */
    public SqlColumn getColumn2() {
        return column2;
    }

    /**
     * @param column2 the column2 to set
     */
    public void setColumn2(SqlColumn column2) {
        this.column2 = column2;
    }

    /**
     * @return the operator
     */
    public SqlOperator2 getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(SqlOperator2 operator) {
        this.operator = operator;
    }
    
}
