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
    public SelectBuilder getSimpleSelect(String from, String alias) {
        FromElem fromElem = new FromElemSimple();
        fromElem.setAlias(alias);
        return new SelectBuilderSimple(new FromElemSimple(from, alias)); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SelectBuilder getSimpleSelect(SqlColumn column, String from, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(Dt value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(Dt value, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(String value, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(int value, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(float value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(float value, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlColumn getValue(double value, String alias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
