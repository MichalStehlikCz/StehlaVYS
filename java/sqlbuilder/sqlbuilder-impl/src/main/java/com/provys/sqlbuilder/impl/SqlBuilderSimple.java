/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.FromElem;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.WhereCond;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * SqlBuilder class used for simple queries (no UNION - just SELECT / FROM
 * / WHERE).
 * 
 * @author stehlik
 */
public class SqlBuilderSimple {

    final List<SqlColumn> columns;
    final Map<String, FromElem> fromElems;
    final WhereCondAnd whereCond;

    public SqlBuilderSimple() {
        this.columns = new ArrayList<>(1);
        this.fromElems = new ConcurrentHashMap<>(1);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(FromElem fromElem) {
        this.columns = new ArrayList<>(1);
        this.fromElems = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null) {
            fromElem.setAlias(fromElem.getDefAlias());
        }
        this.fromElems.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column, FromElem fromElem) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.fromElems = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null) {
            fromElem.setAlias(fromElem.getDefAlias());
        }
        this.fromElems.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
    }

    public SqlBuilderSimple(SqlColumn column, FromElem fromElem
            , WhereCond whereCond) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.fromElems = new ConcurrentHashMap<>(1);
        if (fromElem.getAlias() == null) {
            fromElem.setAlias(fromElem.getDefAlias());
        }
        this.fromElems.put(fromElem.getAlias(), fromElem);
        this.whereCond = new WhereCondAnd();
        this.whereCond.add(whereCond);
    }

    private boolean hasJoinSql() {
        JoinSqlCounter joinSqlCounter = new JoinSqlCounter();
        this.fromElems.forEach(joinSqlCounter);
        return joinSqlCounter.getCount()>0;
    }
    
    public void buildSql(CodeBuilder code) {
        code.appendLine("SELECT").increaseTempIdent("  ", ", ", 4);
        columns.forEach((column) -> {
            column.buildSql(code, true);
        });
        code.removeTempIdent().appendLine("FROM").increaseTempIdent("  ", ", ", 4);
        fromElems.forEach((alias, fromElem) -> {
            fromElem.buildSql(code);
        });
        code.removeTempIdent();
        if (!whereCond.isEmpty() | hasJoinSql()) {
            code.appendLine("WHERE").increaseTempIdent(6);
            fromElems.forEach((alias, fromElem) -> {
                fromElem.buildJoinSql(code);
            });
            whereCond.buildWhereNoBrackets(code);
            code.removeTempIdent();
        }
    }

    public List<SqlColumn> getColumns() {
        return Collections.unmodifiableList(columns);
    }
    
    public SqlBuilderSimple addFromElem(FromElem fromElem) {
        if (fromElem.getAlias() == null) {
            addFromElemUniqueAlias(fromElem);
        } else {
            if (fromElems.putIfAbsent(fromElem.getAlias(), fromElem) != null) {
                throw new DuplicateAliasException(fromElem.getAlias());
            }
        }
        return this;
    }
    
    public SqlBuilderSimple addFromElemUniqueAlias(FromElem fromElem) {
        String defAlias;
        if (fromElem.getAlias() == null) {
            defAlias = fromElem.getDefAlias();
        } else {
            defAlias = fromElem.getAlias();
        }
        String alias = defAlias;
        int index = 1;
        while (this.fromElems.containsKey(alias)) {
            alias = defAlias + ++index;
        }
        fromElem.setAlias(alias);
        fromElems.put(fromElem.getAlias(), fromElem);
        return this;
    }
    
    public SqlBuilderSimple addWhereCond(WhereCond whereCond) {
        this.whereCond.add(whereCond);
        return this;
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
    
    private class JoinSqlCounter implements BiConsumer<String, FromElem> {
        
        int count = 0;
        
        @Override
        public void accept(String alias, FromElem fromElem) {
            if (fromElem.hasJoinSql()) {
                count++;
            }
        }
        
        int getCount() {
            return this.count;
        }
        
    }
}
