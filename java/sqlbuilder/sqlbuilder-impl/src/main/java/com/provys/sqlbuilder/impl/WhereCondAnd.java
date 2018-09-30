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
 * Holds multiple conditions, connected by AND operator.
 * 
 * @author stehlik
 */
public class WhereCondAnd implements WhereCond {
    
    private final List<WhereCond> conditions;
    
    public WhereCondAnd() {
        this.conditions = new ArrayList<>(2);
    }
    
    @Override
    public void buildWhere(CodeBuilder code) {
        if (getNonEmptyCount() > 1)
            code.appendLine("(").increaseTempIdent("    ", "AND ", 2);
        conditions.forEach((condition) -> {
            if (condition instanceof WhereCondAnd)
                ((WhereCondAnd) condition).buildWhereNoBrackets(code);
            else
                condition.buildWhere(code);
        });
        if (getNonEmptyCount() > 1)
            code.removeTempIdent().appendLine(")");
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

    private class MinCostCounter implements Consumer<WhereCond> {
        
        private int minCost = 1000;
        
        public MinCostCounter() {
        }

        @Override
        public void accept(WhereCond whereCond) {
            int cost = whereCond.getCost();
            if (cost < minCost)
                minCost = cost;
        }
        
        public int getCost() {
            return minCost;
        }
    }

   @Override
    public int getCost() {
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

    public void add(WhereCond whereCond) {
        this.conditions.add(whereCond);
    }
}
