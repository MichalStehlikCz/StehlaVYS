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
import java.util.function.Consumer;

/**
 * Holds multiple conditions, connected by OR operator.
 * 
 * @author stehlik
 */
public class WhereCondOr implements WhereCond {
    
    private final List<WhereCond> conditions;
    
    public WhereCondOr() {
        this.conditions = new ArrayList<>(2);
    }
    
    @Override
    public void buildWhere(CodeBuilder code) {
        if (getNonEmptyCount() > 1) {
            code.appendLine("(").increaseTempIdent("    ", "OR  ", 2);
        }
        conditions.forEach((WhereCond condition) -> {
            if (condition instanceof WhereCondOr) {
                ((WhereCondOr) condition).buildWhereNoBrackets(code);
            } else {
                condition.buildWhere(code);
            }
        });
        if (conditions.size() > 1) {
            code.removeTempIdent().appendLine(")");
        }
    }

    /**
     * Build where clause without enclosing brackets and increased ident.
     * Primary use is in nested WhereCondAnd, where conditions from all levels
     * can be placed on the same level
     * 
     * @param code is CodeBuilder used to build statement text 
     */
    public void buildWhereNoBrackets(CodeBuilder code) {
        conditions.forEach((condition) -> {condition.buildWhere(code);});
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
    
    private class MaxCostCounter implements Consumer<WhereCond> {
        
        private double maxCost = 0;
        
        MaxCostCounter() {
        }

        @Override
        public void accept(WhereCond whereCond) {
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
