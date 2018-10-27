/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.BindValue;
import com.provys.provysdb.call.BindVariable;
import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.call.SqlStatement;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlSelectBuilder;
import com.provys.sqlbuilder.iface.SqlWhereCond;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple SELECT statement builder implementation.
 * Represents simple (e.g. no UNION etc.) SELECT statement
 * 
 * @author stehlik
 */
class SqlSelectBuilderSimple extends SqlBuilderSimple
        implements SqlSelectBuilder {
    
    public SqlSelectBuilderSimple() {
    }

    public SqlSelectBuilderSimple(SqlFromElem fromElem) {
        super(fromElem);
    }

    public SqlSelectBuilderSimple(SqlColumn column, SqlFromElem fromElem) {
        super(column, fromElem);
    }

    public SqlSelectBuilderSimple(SqlColumn column, SqlFromElem fromElem
            , SqlWhereCond whereCond) {
        super(column, fromElem, whereCond);
    }

    @Override
    public SqlStatement getSqlStatement() {
        CodeBuilder code = new CodeBuilderImpl();
        this.buildSql(code);
        SqlCallColumnsBuilder columnsBuilder = new SqlCallColumnsBuilder(
                getColumns().size());
        getColumns().forEach(columnsBuilder);
        final List<BindVariable> bindVariables
                = new ArrayList<>(code.getBindVariables().size());
        code.getBindVariables().forEach((bindVariable) -> {
            bindVariables.add(bindVariable);
        });
        return new SqlStatement(code.getCode(), bindVariables
                , columnsBuilder.getColumns());
    }

    @Override
    public SqlCall getSqlCall() {
        CodeBuilder code = new CodeBuilderImpl();
        this.buildSql(code);
        SqlCallColumnsBuilder columnsBuilder = new SqlCallColumnsBuilder(
                getColumns().size());
        getColumns().forEach(columnsBuilder);
        final List<BindValue> bindValues
                = new ArrayList<>(code.getBindVariables().size());
        code.getBindVariables().forEach((bindVariable) -> {
            if ((bindVariable != null) && !(bindVariable instanceof BindValue)) {
                throw new CannotCreateSqlCallNoValueException(
                        bindVariable.getName());
            }
            bindValues.add((BindValue) bindVariable);
        });
        return new SqlCall(code.getCode(), bindValues
                , columnsBuilder.getColumns());
    }

    @Override
    public SqlSelectBuilderSimple addColumn(SqlColumn sqlColumn) {
        super.addColumn(sqlColumn);
        return this;
    }

    @Override
    public SqlSelectBuilderSimple addFromElem(SqlFromElem fromElem) {
        super.addFromElem(fromElem);
        return this;
    }

    @Override
    public SqlSelectBuilderSimple addWhereCond(SqlWhereCond whereCond) {
        super.addWhereCond(whereCond);
        return this;
    }
    
    /**
     * Cannot create SqlCall from builder, supplied variable doesn't have value
     */
    @SuppressWarnings("PublicInnerClass")
    static public class CannotCreateSqlCallNoValueException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        CannotCreateSqlCallNoValueException(String name) {
            super("Cannot create SqlCall, supplied variable doesn't have"
                    +" value: " + name);
        }
    }
}
