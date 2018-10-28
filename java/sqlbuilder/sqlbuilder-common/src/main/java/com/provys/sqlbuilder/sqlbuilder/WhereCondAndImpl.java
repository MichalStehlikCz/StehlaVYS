/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCondAnd;

/**
 * Holds multiple conditions, connected by AND operator.
 * 
 * @author stehlik
 */
class WhereCondAndImpl implements SqlWhereCondAnd {
    
    private final List<SqlWhereCond> conditions;
    
    public WhereCondAndImpl() {
        this.conditions = new ArrayList<>(2);
    }
    
    @Override
    public void buildWhere(CodeBuilder code) {
        if (getNonEmptyCount() > 1) {
            code.increaseTempIdentAnd();
        }
        conditions.forEach((condition) -> {
            condition.buildWhere(code);
        });
        if (getNonEmptyCount() > 1) {
            code.removeTempIdent();
        }
    }

   @Override
    public double getCost() {
        MinCostCounter minCost = new MinCostCounter();
        conditions.forEach(minCost);
        return minCost.getCost();
    }

    private int getNonEmptyCount() {
        NonEmptyWhereCondCounter nonEmptyCounter = new NonEmptyWhereCondCounter();
        conditions.forEach(nonEmptyCounter);
        return nonEmptyCounter.getNonEmptyCount();
    }

    @Override
    public boolean isEmpty() {
        return (getNonEmptyCount() == 0);
    }

    @Override
    public SqlWhereCondAnd add(SqlWhereCond whereCond) {
        this.conditions.add(whereCond);
        return this;
    }
    private class MinCostCounter implements Consumer<SqlWhereCond> {
        
        private double minCost = 1000;
        
        MinCostCounter() {
        }
        
        @Override
        public void accept(SqlWhereCond whereCond) {
            double cost = whereCond.getCost();
            if (cost < minCost) {
                minCost = cost;
            }
        }
        
        public double getCost() {
            return minCost;
        }
    }
}
