/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.SqlCall;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.FromElem;
import com.provys.sqlbuilder.iface.SelectBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.WhereCond;

/**
 * Simple SELECT statement builder implementation.
 * Represents simple (e.g. no UNION etc.) SELECT statement
 * 
 * @author stehlik
 */
public class SelectBuilderSimple extends SqlBuilderSimple
        implements SelectBuilder {
    
    public SelectBuilderSimple() {
    }

    public SelectBuilderSimple(FromElem fromElem) {
        super(fromElem);
    }

    public SelectBuilderSimple(SqlColumn column, FromElem fromElem) {
        super(column, fromElem);
    }

    public SelectBuilderSimple(SqlColumn column, FromElem fromElem
            , WhereCond whereCond) {
        super(column, fromElem, whereCond);
    }

    @Override
    public SqlCall getSqlCall() {
        SqlCall sqlCall = new SqlCall();
        CodeBuilder code = new CodeBuilderImpl();
        this.buildSql(code);
        SqlCallColumnsBuilder columnsBuilder = new SqlCallColumnsBuilder(
                this.columns.size());
        this.columns.forEach(columnsBuilder);
        sqlCall.setColumns(columnsBuilder.getColumns());
        sqlCall.setSql(code.getCode());
        sqlCall.setValues(code.getBindValues());
        return sqlCall;
    }

    @Override
    public SelectBuilder addColumn(SqlColumn sqlColumn) {
        this.columns.add(sqlColumn);
        return this;
    }

    @Override
    public SelectBuilderSimple addFromElem(FromElem fromElem) {
        super.addFromElem(fromElem);
        return this;
    }

    @Override
    public SelectBuilderSimple addWhereCond(WhereCond whereCond) {
        super.addWhereCond(whereCond);
        return this;
    }
    
}
