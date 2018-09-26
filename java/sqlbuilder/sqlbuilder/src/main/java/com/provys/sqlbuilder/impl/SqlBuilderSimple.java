/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.FromElem;
import com.provys.sqlbuilder.iface.SqlBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.WhereCond;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SqlBuilder class used for simple queries (no UNION - just SELECT / FROM
 * / WHERE).
 * 
 * @author stehlik
 */
public class SqlBuilderSimple implements SqlBuilder {

    final List<SqlColumn> columns;
    final Map<String, FromElem> fromElems;
    final WhereCondAnd whereCond;

    public SqlBuilderSimple() {
        this.columns = new ArrayList<>(1);
        this.fromElems = new ConcurrentHashMap<>(1);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column, FromElem fromElem) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.fromElems = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null)
            fromElem.setAlias(fromElem.getDefAlias());
        this.fromElems.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column, FromElem fromElem
            , WhereCond whereCond) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.fromElems = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null)
            fromElem.setAlias(fromElem.getDefAlias());
        this.fromElems.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
        this.whereCond.add(whereCond);
    }

    @Override
    public void buildSql(CodeBuilder code) {
        code.appendLine("SELECT").increaseTempIdent("  ", ", ", 4);
        columns.forEach((column) -> {
            column.buildSql(code);
        });
        code.removeTempIdent().appendLine("FROM").increaseTempIdent("  ", ", ", 4);
        fromElems.forEach((alias, fromElem) -> {
            fromElem.buildSql(code);
        });
        code.removeTempIdent();
        if (!whereCond.isEmpty()) {
            code.appendLine("WHERE").increaseTempIdent(6);
            whereCond.buildWhereNoBrackets(code);
            code.removeTempIdent();
        }
    }

    @Override
    public void buildExistsSql(CodeBuilder code, List<SqlColumn> equalColumns) {
        code.appendLine("SELECT").appendLine("    1").appendLine("FROM")
                .increaseTempIdent("  ", ", ", 4);
        fromElems.forEach((alias, fromElem) -> {
            fromElem.buildSql(code);
        });
        code.removeTempIdent();
        code.appendLine("WHERE").increaseTempIdent("    ", "AND ", 6);
        whereCond.buildWhereNoBrackets(code);
        for (int i = 0; i < columns.size(); i++) {
            code.append("(");
            columns.get(i).buildSqlNoNewLine(code);
            code.append("=");
            equalColumns.get(i).buildSqlNoNewLine(code);
            code.appendLine(")");
        }
        code.removeTempIdent();
    }

    @Override
    public int getCost() {
        return whereCond.getCost();
    }

    @Override
    public List<SqlColumn> getColumns() {
        return columns;
    }
    
    public void addColumn(SqlColumn column) {
        columns.add(column);
    }
    
    public void addFromElem(FromElem fromElem) {
        if (fromElem.getAlias() == null)
            addFromElemUniqueAlias(fromElem);
        else
            if (fromElems.putIfAbsent(fromElem.getAlias(), fromElem) != null)
                throw new DuplicateAliasException(fromElem.getAlias());
    }
    
    public void addFromElemUniqueAlias(FromElem fromElem) {
        String defAlias;
        if (fromElem.getAlias() == null)
            defAlias = fromElem.getDefAlias();
        else
            defAlias = fromElem.getAlias();
        String alias = defAlias;
        int index = 1;
        while (this.fromElems.containsKey(alias)) {
            alias = defAlias + ++index;
        }
        fromElem.setAlias(alias);
        fromElems.put(fromElem.getAlias(), fromElem);
    }
    
    public void addWhereCond(WhereCond whereCond) {
        this.whereCond.add(whereCond);
    }

    /**
     * Alias already exists in query.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DuplicateAliasException extends ProvysException {

        private static final long serialVersionUID = 1L;

        DuplicateAliasException(String alias) {
            super("Alias is already used " + alias);
        }
    }
}
