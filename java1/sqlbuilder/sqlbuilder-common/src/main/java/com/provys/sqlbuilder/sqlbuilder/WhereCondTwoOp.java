/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlOperator2;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;
import java.util.Objects;

/**
 * Builds condition, based on two columns (might be constants) and operator.
 * 
 * @author stehlik
 */
class WhereCondTwoOp implements SqlWhereCond {
    
    private final boolean negative;
    private final SqlColumn column1;
    private final SqlOperator2 operator;
    private final SqlColumn column2;
    
    public WhereCondTwoOp(SqlColumn column1, SqlOperator2 operator
            , SqlColumn column2) {
        Objects.requireNonNull(column1, "Column1 is mandatory");
        Objects.requireNonNull(operator, "Operator is mandatory");
        Objects.requireNonNull(column2, "Column2 is mandatory");
        this.negative = false;
        this.column1 = column1;
        this.operator = operator;
        this.column2 = column2;
    }

    public WhereCondTwoOp(SqlColumn column1, SqlOperator2 operator
            , SqlColumn column2, boolean negative) {
        Objects.requireNonNull(column1, "Column1 is mandatory");
        Objects.requireNonNull(operator, "Operator is mandatory");
        Objects.requireNonNull(column2, "Column2 is mandatory");
        this.negative = negative;
        this.column1 = column1;
        this.column2 = column2;
        this.operator = operator;
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        code.append("(");
        getColumn1().buildSqlNoNewLine(code, false);
        code.append(this.isNegative()?getOperator().getNegOperator()
                :getOperator().getOperator());
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
     * @return the column1
     */
    public SqlColumn getColumn1() {
        return column1;
    }

    /**
     * @return the column2
     */
    public SqlColumn getColumn2() {
        return column2;
    }

    /**
     * @return the operator
     */
    public SqlOperator2 getOperator() {
        return operator;
    }

}
