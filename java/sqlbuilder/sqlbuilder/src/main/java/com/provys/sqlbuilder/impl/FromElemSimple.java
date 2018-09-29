/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.FromElem;

/**
 * Class represents table or expression placed in FROM clause.
 * It is optional to specify join condition that connects this table with
 * some other FromElem(s).
 * 
 * @author stehlik
 */
public class FromElemSimple extends FromElem {
    
    String table;
    
    @Override
    public void buildSql(CodeBuilder code) {
        code.append(table);
        appendAlias(code);
        code.appendLine();
    }

    @Override
    public void buildJoinSql(CodeBuilder code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasJoinSql() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
