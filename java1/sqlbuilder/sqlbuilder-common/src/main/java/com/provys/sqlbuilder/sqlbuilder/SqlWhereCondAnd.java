/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

/**
 * Represents where condition with expressions combined using AND operator.
 * 
 * @author stehlik
 */
public interface SqlWhereCondAnd extends SqlWhereCond {

    /**
     * Get AND where condition.
     * 
     * @return empty AND where condition.
     */
    public static SqlWhereCondAnd create() {
        return new WhereCondAndImpl();
    }

    /**
     * Add supplied where condition to this condition, combine using AND.
     * 
     * @param whereCond is condition to be added to expression
     * @return self to support chaining
     */
    public SqlWhereCondAnd add(SqlWhereCond whereCond);
}
