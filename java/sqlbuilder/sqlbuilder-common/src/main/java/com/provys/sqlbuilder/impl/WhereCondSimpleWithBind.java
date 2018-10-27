/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.iface.CodeBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Extension of WhereCondSimple that allows to define simple (text) where
 * condition, but supports associated bind values.
 * 
 * @author stehlik
 */
class WhereCondSimpleWithBind extends WhereCondSimple {
   
    private List<BindVariable> bindVariables;

    public WhereCondSimpleWithBind(String sql, List<BindVariable> bindVariables)
    {
        super(sql);
        this.bindVariables = new ArrayList<>(bindVariables);
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        if (getSql() == null)
            throw new ConditionNotSpecifiedException();
        code.appendLine(getSql()).addBind(this.bindVariables);
    }

    /**
     * @return the binds
     */
    public List<BindVariable> getBinds() {
        return Collections.unmodifiableList(bindVariables);
    }

    /**
     * @param binds the binds to set
     */
    public void setBinds(List<BindVariable> binds) {
        this.bindVariables = new ArrayList<>(bindVariables);
    }
    
}
