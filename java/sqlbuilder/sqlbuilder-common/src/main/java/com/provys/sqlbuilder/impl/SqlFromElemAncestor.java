/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlFromElem;

/**
 * SqlFromElemAncestor implements alias handling for SqlFromElem.
 * 
 * @author stehlik
 */
public abstract class SqlFromElemAncestor implements SqlFromElem {

    private String alias;
 
    public SqlFromElemAncestor() {
    }

    public SqlFromElemAncestor(String alias) {
        this.alias = alias;
    }
    
    /**
     * Appends alias (if set) to supplied CodeBuilder.
     * 
     * @param code is CodeBuilder used to build SQL statement.
     */
    protected void appendAlias(CodeBuilder code) {
        if (getAlias() != null) {
            code.append(getAlias());
        }
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    @Override
    public String getDefAlias() {
        return "al";
    }

}
