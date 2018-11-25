/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

/**
 * Represents where condition with expressions combined using OR operator.
 * 
 * @author stehlik
 */
public interface SqlWhereCondOr extends SqlWhereCond {
    
    /**
     * Get OR where condition.
     * 
     * @return empty OR where condition.
     */
    public static SqlWhereCondOr create() {
        return new WhereCondOrImpl();
    }


    /**
     * Add supplied where condition to this condition, combine using OR.
     * 
     * @param whereCond is condition to be added to expression
     * @return self to support chaining
     */
    public SqlWhereCondOr add(SqlWhereCond whereCond);
}
