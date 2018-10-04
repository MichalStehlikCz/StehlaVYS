/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.WhereCond;
import java.util.function.Consumer;

/**
 * Class enables counting number of non-empty where conditions in collection.
 * Used from within WhereCondAnd and WhereCondOr.
 * 
 * @author stehlik
 */
class NonEmptyWhereCondCounter implements Consumer<WhereCond> {
        
    private int nonEmptyCount = 0;
        
    public NonEmptyWhereCondCounter() {
    }

    @Override
    public void accept(WhereCond whereCond) {
        if (!whereCond.isEmpty())
            nonEmptyCount++;
    }
        
    public int getNonEmptyCount() {
        return nonEmptyCount;
    }
}

