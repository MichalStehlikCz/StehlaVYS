/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.datatypes.Dt;
import com.provys.sqlbuilder.iface.FromElem;
import com.provys.sqlbuilder.iface.SelectBuilder;
import com.provys.sqlbuilder.iface.SqlBuilderFactory;
import com.provys.sqlbuilder.iface.SqlColumn;

/**
 * Implementation of factory, producing SqlBuilder and related objects.
 * 
 * @author stehlik
 */
public class SqlBuilderFactoryImpl implements SqlBuilderFactory {
    
    @Override
    public SelectBuilder getSimpleSelect(String table, String alias) {
        return new SelectBuilderSimple(getFromElem(table, alias));
    }

    @Override
    public SelectBuilder getSimpleSelect(SqlColumn column, String table, String alias) {
        return new SelectBuilderSimple(column, getFromElem(table, alias));
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
    public FromElem getFromElem(String table, String alias) {
        FromElemSimple fromElem = new FromElemSimple();
        fromElem.setAlias(alias);
        fromElem.setTable(table);
        return fromElem;
    }
}
