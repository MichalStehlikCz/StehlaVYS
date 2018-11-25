/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.error.ProvysException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class represents table or expression placed in FROM clause.
 * It is optional to specify join condition that connects this table with
 * some other FromElem(s).
 * 
 * @author stehlik
 */
class SqlFromElemSimple extends AbstractSqlFromElem {
    
    /**
     * Creates simple from element using supplied table or view.
     * Verifies that passed argument is proper table or view name. Constructs
     * alias from supplied table name using PROVYS naming conventions (e.g. if
     * name starts with 2-3 characters followed by underscore, removes prefix,
     * if name ends with underscore and two character suffix, removes suffix)
     * 
     * @param table
     * @return element based on table
     */
    public static SqlFromElemSimple ofTable(String table) {
        return ofTable(table, null);
    }

    /**
     * Creates simple from element using supplied table or view.
     * Verifies that passed argument is proper table or view name. If passed
     * alias is empty or null, it constructs alias from supplied table name
     * using PROVYS naming conventions (e.g. if name starts with 2-3 characters
     * followed by underscore, prefix is removed, if name ends with underscore
     * and two character suffix, removes suffix)
     * 
     * @param table is table or view name
     * @param alias is alias to be assigned to from element; might be null or
     * empty, default alias is construed from table name in such situation
     * @return element based on table with specified suffix
     */
    public static SqlFromElemSimple ofTable(String table, String alias) {
        Objects.requireNonNull(table, "null passed as table to from element");
        if (table.isEmpty()) {
            throw new ProvysException(
                    "Empty table name supplied to from element");
        }
        if (!table.matches("[a-zA-Z][a-zA-Z_0-9]*")) {
            throw new ProvysException(
                    "Table name does not match SQL name pattern");
        }
        String effAlias = alias;
        if ((alias == null) || alias.isEmpty()) {
            effAlias = table;
        }
        return new SqlFromElemSimple(table, effAlias);
    }

    /**
     * Creates simple from element using supplied SQL expression.
     * Does no checking on content of supplied expression; encloses this
     * expression in brackets. Alias is mandatory in this version of constructor
     * 
     * @param sqlExpression is SELECT statement used to construct FROM element
     * @return 
     */
    public static SqlFromElemSimple ofExpression(String sqlExpression
            , String alias) {
        Objects.requireNonNull(sqlExpression, "null passed as SQL expression to from element");
        if (sqlExpression.isEmpty()) {
            throw new ProvysException(
                    "Empty SQL expression supplied to from element");
        }
        Objects.requireNonNull(alias, "null passed as alias to from element based on expression");
        if (sqlExpression.isEmpty()) {
            throw new ProvysException(
                    "Empty alias supplied to from element built on expression");
        }
        return new SqlFromElemSimple("(\n" + sqlExpression + "\n)", alias);
    }

    private final String table;
    private boolean outerJoin = false;
    private final List<SqlColumnJoin> columns;
    
    private SqlFromElemSimple(String table, String alias) {
        super(alias);
        this.table = table;
        this.columns = Collections.emptyList();
    }

    @Override
    public void buildSql(CodeBuilder code) {
        code.append(getTable());
        appendAlias(code);
        code.appendLine();
    }

    @Override
    public void buildJoinSql(CodeBuilder code) {
        columns.forEach((sqlColumnJoin) -> {
            code.append("(").increaseTempIdent(2);
            sqlColumnJoin.getSource().buildSqlNoNewLine(code, false);
            if (outerJoin) {
                code.append("(+)");
            }
            code.append("=");
            sqlColumnJoin.getTarget().buildSqlNoNewLine(code, false);
            code.removeTempIdent().append(")");
        });
    }

    @Override
    public boolean hasJoinSql() {
        return this.columns.size()>0;
    }

    /**
     * @return the table
     */
    public String getTable() {
        return table;
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
     * @return the columns
     */
    public List<SqlColumnJoin> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(List<SqlColumnJoin> columns) {
        this.columns.clear();
        this.columns.addAll(columns);
    }
}
