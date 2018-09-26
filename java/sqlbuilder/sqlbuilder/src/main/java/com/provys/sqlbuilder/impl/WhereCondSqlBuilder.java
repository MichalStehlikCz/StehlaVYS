/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.JoinType;
import static com.provys.sqlbuilder.iface.JoinType.*;
import java.util.List;
import com.provys.sqlbuilder.iface.SqlBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.WhereCond;
import java.util.ArrayList;

/**
 * Condition built using SqlBuilder.
 * In general results in IN or EXISTS condition
 * 
 * @author stehlik
 */
public class WhereCondSqlBuilder implements WhereCond {
    
    private List<SqlColumn> columns;
    private SqlBuilder subquery;
    private JoinType joinType = null; // without preference by default - it
    // defers decision to query cost
    
    public WhereCondSqlBuilder() {
        this.columns = new ArrayList<>(1);
    }
    
    public WhereCondSqlBuilder(SqlColumn column, SqlBuilder subquery) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.subquery = subquery;
        checkColumns();
    }
    
    public WhereCondSqlBuilder(SqlColumn column, SqlBuilder subquery
            , JoinType joinType)
    {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.subquery = subquery;
        this.joinType = joinType;
        checkColumns();
    }

    public WhereCondSqlBuilder(List<SqlColumn> columns, SqlBuilder subquery) {
        this.columns = new ArrayList<>(columns);
        this.subquery = subquery;
        checkColumns();
    }

    public WhereCondSqlBuilder(List<SqlColumn> columns, SqlBuilder subquery
            , JoinType joinType) {
        this.columns = new ArrayList<>(columns);
        this.subquery = subquery;
        this.joinType = joinType;
        checkColumns();
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        checkUseable();
        JoinType useJoinType = this.joinType;
        if (useJoinType == null)
            if (subquery.getCost() > 10)
                useJoinType = EXISTS;
            else
                useJoinType = IN;
        switch (useJoinType) {
            case EXISTS:
                code.appendLine("EXISTS(").increaseTempIdent(4);
                subquery.buildExistsSql(code, this.columns);
                code.removeTempIdent().appendLine("  )");
                break;
            case IN:
            case JOIN: // join has to be done when adding condition, not usable
                       // in condition itself..
                code.append("(");
                if (columns.size() == 1)
                    columns.get(0).buildSqlNoNewLine(code);
                else {
                    code.appendLine().appendLine("  (")
                            .increaseTempIdent("  ", ", ", 6);
                    columns.forEach((column) -> {column.buildSql(code);});
                    code.removeTempIdent().append("  )");
                }
                code.appendLine(" IN (").increaseTempIdent(2);
                subquery.buildSql(code);
                code.removeTempIdent().appendLine(")");
                break;
        }
    }

    @Override
    public int getCost() {
        if (getSubquery() == null)
            throw new SubqueryMissingException();
        return getSubquery().getCost();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    
    private void checkColumns() {
        if ((this.columns != null) & (this.subquery != null))
            if (this.columns.size() != this.subquery.getColumns().size())
                throw new ColumnsNumberMismatchException();
    }

    private void checkUseable() {
        if (this.columns == null)
            throw new ColumnsMissingException();
        if (this.subquery == null)
            throw new SubqueryMissingException();
        checkColumns();
    }

    /**
     * @return the columns
     */
    public List<SqlColumn> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(List<SqlColumn> columns) {
        this.columns = new ArrayList<>(columns);
        checkColumns();
    }

    /**
     * @return the subquery
     */
    public SqlBuilder getSubquery() {
        return subquery;
    }

    /**
     * @param subquery the subquery to set
     */
    public void setSubquery(SqlBuilder subquery) {
        this.subquery = subquery;
        checkColumns();
    }

    /**
     * @return the joinType
     */
    public JoinType getJoinType() {
        return joinType;
    }

    /**
     * @param joinType the joinType to set
     */
    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    /**
     * Trying to build condition that does not have subquery set.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class SubqueryMissingException extends ProvysException {

        private static final long serialVersionUID = 1L;

        SubqueryMissingException() {
            super("Cannot use subquery condition with no subqueries specified");
        }
    }

    /**
     * Trying to build condition that does not have column(s) set.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnsMissingException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ColumnsMissingException() {
            super("Cannot use subquery condition with no columns specified");
        }
    }

    /**
     * Number of columns does not match subquery.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnsNumberMismatchException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ColumnsNumberMismatchException() {
            super("Number of columns does not match subquery");
        }
    }
}
