/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.BindValue;
import com.provys.sqlbuilder.iface.CodeBuilder;

/**
 * Sql column, containing single bind value.
 * Often used as basis for construction of simple queries using retype.
 * 
 * @author stehlik
 */
public class SqlColumnBind extends SqlColumnAncestor {
    
    private final BindValue bindValue;

    SqlColumnBind(BindValue bindValue) {
        super(bindValue.getTypeClass(), true);
        this.bindValue = bindValue;
    }
    
    SqlColumnBind(BindValue bindValue, String alias) {
        super(alias, bindValue.getTypeClass(), true);
        this.bindValue = bindValue;
    }
    
    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        code.append(":").append(bindValue.getName()).addBind(bindValue);
    }

    /**
     * @return the bindValue
     */
    public BindValue getBindValue() {
        return bindValue;
    }

}
