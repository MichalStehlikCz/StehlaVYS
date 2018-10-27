/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.iface.SqlBuilderFactory;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlQueryBuilder;
import com.provys.sqlbuilder.iface.SqlSelectBuilder;

/**
 * Implementation of factory, producing SqlBuilder and related objects.
 * 
 * @author stehlik
 */
class SqlBuilderFactoryImpl implements SqlBuilderFactory {
    
    @Override
    public SqlSelectBuilder getSimpleSelect(String table, String alias) {
        return new SqlSelectBuilderSimple(getFromElem(table, alias));
    }

    @Override
    public SqlSelectBuilder getSimpleSelect(SqlColumn column, String table, String alias) {
        return new SqlSelectBuilderSimple(column, getFromElem(table, alias));
    }

    @Override
    public SqlQueryBuilder getBindQuery(BindVariable bindVariable) {
        return new QueryBuilderSimple(getBindColumn(bindVariable));
    }

    @Override
    public SqlColumn getValue(Dt value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(Dt value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(String value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(String value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(int value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(int value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(float value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(float value, String alias) {
        return SqlColumnValue.of(value, alias);
    }

    @Override
    public SqlColumn getValue(double value) {
        return SqlColumnValue.of(value);
    }

    @Override
    public SqlColumn getValue(double value, String alias) {
        return SqlColumnValue.of(value, alias);
    }
    
    @Override
    public SqlColumn getBindColumn(BindVariable bindVariable) {
        return SqlColumnBind.of(bindVariable);
    }
    
    @Override
    public SqlColumn getBindColumn(BindVariable bindVariable, String alias) {
        return SqlColumnBind.of(bindVariable, alias);
    }

    @Override
    public SqlFromElem getFromElem(String table, String alias) {
        return new SqlFromElemSimple(table, alias);
    }
}
