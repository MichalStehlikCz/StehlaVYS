/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.provysdb.call.BindValue;
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
public class WhereCondSimpleWithBind extends WhereCondSimple {
   
    private List<BindValue> bindValues;

    public WhereCondSimpleWithBind(String sql, List<BindValue> bindValues) {
        super(sql);
        this.bindValues = new ArrayList<>(bindValues);
    }

    @Override
    public void buildWhere(CodeBuilder code) {
        code.addLine(getSql()).addBind(this.bindValues);
    }

    /**
     * @return the binds
     */
    public List<BindValue> getBinds() {
        return Collections.unmodifiableList(bindValues);
    }

    /**
     * @param binds the binds to set
     */
    public void setBinds(List<BindValue> binds) {
        this.bindValues = new ArrayList<>(bindValues);
    }
    
}
