/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;

/**
 * Simple Where condition, specified as String.
 * 
 * @author stehlik
 */
class WhereCondSimple implements SqlWhereCond {
    
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
        if (sql == null) {
            throw new ConditionNotSpecifiedException();
        }
        code.appendLine(getSql());
    }

    @Override
    public double getCost() {
        return 1000;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    
    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }
    
    /**
     * Condition was not specified
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ConditionNotSpecifiedException extends ProvysException {

        private static final long serialVersionUID = 1L;

        ConditionNotSpecifiedException() {
            super("Condition was not specified");
        }
    }
}
