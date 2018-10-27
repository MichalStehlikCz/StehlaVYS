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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.provys.sqlbuilder.iface.SqlQueryBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlWhereCond;

/**
 * Condition built using SqlBuilder.
 * In general results in IN or EXISTS condition
 * 
 * @author stehlik
 */
class WhereCondQueryBuilder implements SqlWhereCond {
    
    private List<SqlColumn> columns;
    private SqlQueryBuilder subquery;
    private JoinType joinType = null; // without preference by default - it
    // defers decision to query cost
    
    public WhereCondQueryBuilder() {
        this.columns = new ArrayList<>(1);
    }
    
    public WhereCondQueryBuilder(SqlColumn column, SqlQueryBuilder subquery) {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.subquery = subquery;
    }
    
    public WhereCondQueryBuilder(SqlColumn column, SqlQueryBuilder subquery
            , JoinType joinType)
    {
        this.columns = new ArrayList<>(1);
        this.columns.add(column);
        this.subquery = subquery;
        this.joinType = joinType;
    }

    public WhereCondQueryBuilder(List<SqlColumn> columns, SqlQueryBuilder subquery) {
        this.columns = new ArrayList<>(columns);
        this.subquery = subquery;
    }

    public WhereCondQueryBuilder(List<SqlColumn> columns, SqlQueryBuilder subquery
            , JoinType joinType) {
        this.columns = new ArrayList<>(columns);
        this.subquery = subquery;
        this.joinType = joinType;
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        checkUseable();
        JoinType useJoinType = this.joinType;
        if (useJoinType == null) {
            if (subquery.getCost() > 10) {
                useJoinType = EXISTS;
            } else {
                useJoinType = IN;
            }
        }
        switch (useJoinType) {
            case EXISTS:
                subquery.buildExistsSql(code, this.columns);
                break;
            case IN:
            case JOIN: // join has to be done when adding condition, not usable
                       // in condition itself..
                subquery.buildInSql(code, this.columns);
                break;
        }
    }

    @Override
    public double getCost() {
        if (getSubquery() == null) {
            throw new SubqueryMissingException();
        }
        return getSubquery().getCost();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    
    private void checkUseable() {
        if (this.columns == null) {
            throw new ColumnsMissingException();
        }
        if (this.subquery == null) {
            throw new SubqueryMissingException();
        }
    }

    /**
     * @return the columns
     */
    public List<SqlColumn> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(List<SqlColumn> columns) {
        this.columns = new ArrayList<>(columns);
    }

    /**
     * @return the subquery
     */
    public SqlQueryBuilder getSubquery() {
        return subquery;
    }

    /**
     * @param subquery the subquery to set
     */
    public void setSubquery(SqlQueryBuilder subquery) {
        this.subquery = subquery;
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

}
