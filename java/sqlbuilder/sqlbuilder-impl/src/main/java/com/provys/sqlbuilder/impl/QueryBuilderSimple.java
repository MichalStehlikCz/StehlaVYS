/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.QueryBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import java.util.List;

/**
 * Basic implementation of QueryBuilder interface, based on SqlBuilder.
 * 
 * @author stehlik
 */
public class QueryBuilderSimple extends SqlBuilderSimple implements QueryBuilder {

    @Override
    public void buildExistsSql(CodeBuilder code, List<SqlColumn> columns) {
        code.appendLine("EXISTS(").increaseTempIdent(4).appendLine("SELECT")
                .appendLine("    1").appendLine("FROM")
                .increaseTempIdent("  ", ", ", 4);
        fromElems.forEach((alias, fromElem) -> {
            fromElem.buildSql(code);
        });
        code.removeTempIdent();
        code.appendLine("WHERE").increaseTempIdent("    ", "AND ", 6);
        whereCond.buildWhereNoBrackets(code);
        for (int i = 0; i < columns.size(); i++) {
            code.append("(");
            this.columns.get(i).buildSqlNoNewLine(code, false);
            code.append("=");
            columns.get(i).buildSqlNoNewLine(code, false);
            code.appendLine(")");
        }
        code.removeTempIdent().removeTempIdent().appendLine("  )");
    }

    @Override
    public void buildInSql(CodeBuilder code, List<SqlColumn> columns) {
                code.append("(");
                if (columns.size() == 1) {
                    columns.get(0).buildSqlNoNewLine(code, false);
                } else {
                    code.appendLine().appendLine("  (")
                            .increaseTempIdent("  ", ", ", 6);
                    columns.forEach((SqlColumn column)
                            -> {column.buildSql(code, false);});
                    code.removeTempIdent().append("  )");
                }
                code.appendLine(" IN (").increaseTempIdent(2);
                this.buildSql(code);
                code.removeTempIdent().appendLine(")");
    }

    @Override
    public double getCost() {
        return whereCond.getCost();
    }

    @Override
    public void checkColumns(List<SqlColumn> columns) {
        if (this.columns != null) {
            if (this.columns.size() != columns.size()) {
                throw new ColumnsNumberMismatchException();
            }
        }
    }

}
