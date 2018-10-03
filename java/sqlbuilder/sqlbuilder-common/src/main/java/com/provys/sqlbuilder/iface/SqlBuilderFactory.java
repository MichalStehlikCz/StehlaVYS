/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.common.datatypes.Dt;

/**
 * Creates instances of basic (not catalogue etc.) SqlBuilder objects.
 * 
 * @author stehlik
 */
public interface SqlBuilderFactory {
    public SelectBuilder getSimpleSelect(String from, String alias);
    public SelectBuilder getSimpleSelect(SqlColumn column, String from
            , String alias);
    
    public SqlColumn getValue(Dt value);
    public SqlColumn getValue(Dt value, String alias);
    public SqlColumn getValue(String value);
    public SqlColumn getValue(String value, String alias);
    public SqlColumn getValue(int value);
    public SqlColumn getValue(int value, String alias);
    public SqlColumn getValue(float value);
    public SqlColumn getValue(float value, String alias);
    public SqlColumn getValue(double value);
    public SqlColumn getValue(double value, String alias);
}
