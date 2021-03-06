/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import java.util.List;
import com.provys.sqlbuilder.sqlbuilder.SqlQueryBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;

/**
 * Basic implementation of SqlQueryBuilder interface, based on SqlBuilder.
 *
 * @author stehlik
 */
class QueryBuilderSimple extends SqlBuilderSimple implements SqlQueryBuilder {

    /**
     * Default constructor for SqlQueryBuilder. Creates empty SqlQueryBuilder.
     */
    public QueryBuilderSimple() {
    }

    /**
     * Creates simple query, containing only column (usually bind value).
     *
     * @param column is column to be placed in query (as a key)
     */
    public QueryBuilderSimple(SqlColumn column) {
        super(column);
    }

    /**
     * Build equality comparison between supplied columns and my columns.
     *
     * @param code
     * @param columns
     */
    private void buildEquals(CodeBuilder code, List<SqlColumn> columns) {
        if (this.getColumns().size() > 1) {
            code.increaseTempIdentAnd();
        }
        for (int i = 0; i < columns.size(); i++) {
            code.append("(");
            this.getColumns().get(i).buildSqlNoNewLine(code, false);
            code.append("=");
            columns.get(i).buildSqlNoNewLine(code, false);
            code.appendLine(")");
        }
        if (this.getColumns().size() > 1) {
            code.removeTempIdent();
        }
    }

    @Override
    public void buildExistsSql(CodeBuilder code, List<SqlColumn> columns) {
        checkColumns(columns);
        if (getFromElems().isEmpty()) { // we can build simple comparison
            buildEquals(code, columns);
        } else {
            code.appendLine("EXISTS(").increaseTempIdent(4).appendLine("SELECT")
                    .appendLine("    1").appendLine("FROM")
                    .increaseTempIdent("  ", ", ", 4);
            getFromElems().forEach((fromElem) -> {
                fromElem.buildSql(code);
            });
            code.removeTempIdent();
            code.appendLine("WHERE").increaseTempIdent(4)
                    .increaseTempIdentAnd();
            getWhereCond().buildWhere(code);
            buildEquals(code, columns);
            code.removeTempIdent().removeTempIdent().removeTempIdent();
        }
    }

    @Override
    public void buildInSql(CodeBuilder code, List<SqlColumn> columns) {
        checkColumns(columns);
        if (getFromElems().isEmpty()) { // we can build simple comparison
            buildEquals(code, columns);
        } else {
            code.append("(");
            if (columns.size() == 1) {
                columns.get(0).buildSqlNoNewLine(code, false);
            } else {
                code.appendLine().appendLine("  (")
                        .increaseTempIdent("  ", ", ", 6);
                columns.forEach((SqlColumn column)
                        -> {
                    column.buildSql(code, false);
                });
                code.removeTempIdent().append("  )");
            }
            code.appendLine(" IN (").increaseTempIdent(2);
            this.buildSql(code);
            code.removeTempIdent().appendLine(")");
        }
    }

    @Override
    public double getCost() {
        return getWhereCond().getCost();
    }

    @Override
    public void checkColumns(List<SqlColumn> columns) {
        if (getColumns().size() != columns.size()) {
            throw new ColumnsNumberMismatchException();
        }
    }

}
