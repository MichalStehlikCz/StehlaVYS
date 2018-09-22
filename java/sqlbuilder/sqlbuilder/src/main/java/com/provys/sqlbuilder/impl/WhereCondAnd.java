/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.WhereCond;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class WhereCondAnd implements WhereCond {
    
    private final List<WhereCond> conditions;
    
    public WhereCondAnd() {
        this.conditions = new ArrayList<>(2);
    }
    
    @Override
    public void buildWhere(CodeBuilder code) {
        code.addLine("(").setTempIdent("    ", "AND ");
        conditions.forEach((condition) -> {condition.buildWhere(code);});
    }

    @Override
    public int getCost() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
