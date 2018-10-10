/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlWhereCond;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * SqlBuilder class used for simple queries (no UNION - just SELECT / FROM
 * / WHERE).
 * 
 * @author stehlik
 */
public class SqlBuilderSimple {

    final private List<SqlColumn> columns;
    final private List<SqlFromElem> fromElems;
    final private Map<String, SqlFromElem> fromElemsByAlias;
    final private WhereCondAnd whereCond;

    public SqlBuilderSimple() {
        this.columns = new ArrayList<>(1);
        this.fromElems = new ArrayList<>(1);
        this.fromElemsByAlias = new ConcurrentHashMap<>(1);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column) {
        this.columns = new ArrayList<>(1);
        columns.add(column);
        this.fromElems = new ArrayList<>(1);
        this.fromElemsByAlias = new ConcurrentHashMap<>(1);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlFromElem fromElem) {
        this.columns = new ArrayList<>(1);
        this.fromElems = new ArrayList<>(1);
        this.fromElemsByAlias = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null) {
            fromElem.setAlias(fromElem.getDefAlias());
        }
        this.fromElems.add(fromElem);
        this.fromElemsByAlias.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column, SqlFromElem fromElem) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.fromElems = new ArrayList<>(1);
        this.fromElemsByAlias = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null) {
            fromElem.setAlias(fromElem.getDefAlias());
        }
        this.fromElems.add(fromElem);
        this.fromElemsByAlias.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column, SqlFromElem fromElem
            , SqlWhereCond whereCond) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.fromElems = new ArrayList<>(1);
        this.fromElemsByAlias = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null) {
            fromElem.setAlias(fromElem.getDefAlias());
        }
        this.fromElems.add(fromElem);
        this.fromElemsByAlias.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
        this.whereCond.add(whereCond);
    }

    private boolean hasJoinSql() {
        JoinSqlCounter joinSqlCounter = new JoinSqlCounter();
        this.getFromElems().forEach(joinSqlCounter);
        return joinSqlCounter.getCount()>0;
    }
    
    public void buildSql(CodeBuilder code) {
        code.appendLine("SELECT").increaseTempIdent("  ", ", ", 4);
        getColumns().forEach((column) -> {
            column.buildSql(code, true);
        });
        code.removeTempIdent().appendLine("FROM").increaseTempIdent("  ", ", ", 4);
        getFromElems().forEach((fromElem) -> {
            fromElem.buildSql(code);
        });
        code.removeTempIdent();
        if (!whereCond.isEmpty() | hasJoinSql()) {
            code.appendLine("WHERE").increaseTempIdent(4)
                    .increaseTempIdentAnd();
            getFromElems().forEach((fromElem) -> {
                fromElem.buildJoinSql(code);
            });
            getWhereCond().buildWhere(code);
            code.removeTempIdent().removeTempIdent();
        }
    }

    public List<SqlColumn> getColumns() {
        return Collections.unmodifiableList(columns);
    }
    
    /**
     * Add column to SqlBuilder.
     * Some descendants might publish this method, but it is declared protected
     * in this ancestor as some descendants (like QueryBuilder) want to keep it
     * private
     * 
     * @param column is column to be added to column collection
     * @return itself to support chaining
     */
    protected SqlBuilderSimple addColumn(SqlColumn column) {
        this.columns.add(column);
        return this;
    }

    /**
     * @return the fromElems
     */
    public List<SqlFromElem> getFromElems() {
        return Collections.unmodifiableList(fromElems);
    }

    public SqlFromElem getFromElemByAlias(String alias) {
        SqlFromElem fromElem = fromElemsByAlias.get(alias);
        if (fromElem == null) {
            throw new ElemNotFoundByAliasException(alias);
        }
        return fromElem;
    }
    
    public SqlFromElem getFromElemByAliasIfExists(String alias) {
        return fromElemsByAlias.get(alias);
    }

    public SqlBuilderSimple addFromElem(SqlFromElem fromElem) {
        if (fromElem.getAlias() == null) {
            addFromElemUniqueAlias(fromElem);
        } else {
            if (fromElemsByAlias.putIfAbsent(fromElem.getAlias(), fromElem) != null) {
                throw new DuplicateAliasException(fromElem.getAlias());
            }
            this.fromElems.add(fromElem);
        }
        return this;
    }
    
    public SqlBuilderSimple addFromElemUniqueAlias(SqlFromElem fromElem) {
        String defAlias;
        if (fromElem.getAlias() == null) {
            defAlias = fromElem.getDefAlias();
        } else {
            defAlias = fromElem.getAlias();
        }
        String alias = defAlias;
        int index = 1;
        while (this.fromElemsByAlias.containsKey(alias)) {
            alias = defAlias + ++index;
        }
        fromElem.setAlias(alias);
        fromElemsByAlias.put(fromElem.getAlias(), fromElem);
        this.fromElems.add(fromElem);
        return this;
    }
    
    /**
     * @return the whereCond
     */
    public WhereCondAnd getWhereCond() {
        return whereCond;
    }

    public SqlBuilderSimple addWhereCond(SqlWhereCond whereCond) {
        this.whereCond.add(whereCond);
        return this;
    }

    /**
     * FROM element was looked up but not found using alias.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ElemNotFoundByAliasException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ElemNotFoundByAliasException(String alias) {
            super("FROM element not found using alias:" + alias);
        }
    }

    /**
     * Alias already exists in query.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class DuplicateAliasException extends ProvysException {

        private static final long serialVersionUID = 1L;

        DuplicateAliasException(String alias) {
            super("Alias is already used: " + alias);
        }
    }
    
    private class JoinSqlCounter implements Consumer<SqlFromElem> {
        
        int count = 0;
        
        @Override
        public void accept(SqlFromElem fromElem) {
            if (fromElem.hasJoinSql()) {
                count++;
            }
        }
        
        int getCount() {
            return this.count;
        }
        
    }

}
