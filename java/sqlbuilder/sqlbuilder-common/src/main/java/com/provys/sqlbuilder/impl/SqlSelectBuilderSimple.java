/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.SqlCall;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlSelectBuilder;
import com.provys.sqlbuilder.iface.SqlWhereCond;

/**
 * Simple SELECT statement builder implementation.
 * Represents simple (e.g. no UNION etc.) SELECT statement
 * 
 * @author stehlik
 */
public class SqlSelectBuilderSimple extends SqlBuilderSimple
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
    public SqlCall getSqlCall() {
        SqlCall sqlCall = new SqlCall();
        CodeBuilder code = new CodeBuilderImpl();
        this.buildSql(code);
        SqlCallColumnsBuilder columnsBuilder = new SqlCallColumnsBuilder(
                getColumns().size());
        getColumns().forEach(columnsBuilder);
        sqlCall.setColumns(columnsBuilder.getColumns());
        sqlCall.setSql(code.getCode());
        sqlCall.setValues(code.getBindValues());
        return sqlCall;
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
    
}
