/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.WhereCond;

/**
 * Simple Where condition, specified as String.
 * 
 * @author stehlik
 */
public class WhereCondSimple implements WhereCond {
    
    private final String sql;
    
    /**
     * Basic constructor, creating simple where condition from supplied String.
     * 
     * @param sql
     */
    public WhereCondSimple(String sql) {
        this.sql = sql;
    }
    
    @Override
    public void buildWhere(CodeBuilder code) {
        code.addLine(getSql());
    }

    @Override
    public int getCost() {
        return 1000;
    }

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }
    
}
