/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.BindValue;
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
public class SqlBuilderFactoryImpl implements SqlBuilderFactory {
    
    @Override
    public SqlSelectBuilder getSimpleSelect(String table, String alias) {
        return new SqlSelectBuilderSimple(getFromElem(table, alias));
    }

    @Override
    public SqlSelectBuilder getSimpleSelect(SqlColumn column, String table, String alias) {
        return new SqlSelectBuilderSimple(column, getFromElem(table, alias));
    }

    @Override
    public SqlQueryBuilder getBindQuery(BindValue bindValue) {
        return new QueryBuilderSimple(getBindColumn(bindValue));
    }

    @Override
    public SqlColumn getValue(Dt value) {
        return new SqlColumnValue(value);
    }

    @Override
    public SqlColumn getValue(Dt value, String alias) {
        return new SqlColumnValue(value, alias);
    }

    @Override
    public SqlColumn getValue(String value) {
        return new SqlColumnValue(value);
    }

    @Override
    public SqlColumn getValue(String value, String alias) {
        return new SqlColumnValue(value, alias);
    }

    @Override
    public SqlColumn getValue(int value) {
        return new SqlColumnValue(value);
    }

    @Override
    public SqlColumn getValue(int value, String alias) {
        return new SqlColumnValue(value, alias);
    }

    @Override
    public SqlColumn getValue(float value) {
        return new SqlColumnValue(value);
    }

    @Override
    public SqlColumn getValue(float value, String alias) {
        return new SqlColumnValue(value, alias);
    }

    @Override
    public SqlColumn getValue(double value) {
        return new SqlColumnValue(value);
    }

    @Override
    public SqlColumn getValue(double value, String alias) {
        return new SqlColumnValue(value, alias);
    }
    
    @Override
    public SqlColumn getBindColumn(BindValue bindValue) {
        return new SqlColumnBind(bindValue);
    }
    
    @Override
    public SqlColumn getBindColumn(BindValue bindValue, String alias) {
        return new SqlColumnBind(bindValue, alias);
    }

    @Override
    public SqlFromElem getFromElem(String table, String alias) {
        return new SqlFromElemSimple(table, alias);
    }
}
