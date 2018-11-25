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
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCondOr;

/**
 * Holds multiple conditions, connected by OR operator.
 * 
 * @author stehlik
 */
class WhereCondOrImpl implements SqlWhereCondOr {
    
    private final List<SqlWhereCond> conditions;
    
    public WhereCondOrImpl() {
        this.conditions = new ArrayList<>(2);
    }
    
    @Override
    public void buildWhere(CodeBuilder code) {
        if (getNonEmptyCount() > 1) {
            code.increaseTempIdentOr();
        }
        conditions.forEach((SqlWhereCond condition) -> {
            condition.buildWhere(code);
        });
        if (conditions.size() > 1) {
            code.removeTempIdent();
        }
    }

    @Override
    public double getCost() {
        MaxCostCounter cost = new MaxCostCounter();
        conditions.forEach(cost);
        return cost.getCost();
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
    public SqlWhereCondOr add(SqlWhereCond whereCond) {
        conditions.add(whereCond);
        return this;
    }

    private class MaxCostCounter implements Consumer<SqlWhereCond> {
        
        private double maxCost = 0;
        
        MaxCostCounter() {
        }

        @Override
        public void accept(SqlWhereCond whereCond) {
            double cost = whereCond.getCost();
            if (cost > maxCost) {
                maxCost = cost;
            }
        }
        
        public double getCost() {
            if (maxCost == 0) {
                return 1000;
            }
            return maxCost;
        }
    }
    
}
