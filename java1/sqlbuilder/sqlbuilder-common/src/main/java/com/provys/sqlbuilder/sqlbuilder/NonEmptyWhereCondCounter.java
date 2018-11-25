/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import java.util.function.Consumer;
import com.provys.sqlbuilder.sqlbuilder.SqlWhereCond;

/**
 * Class enables counting number of non-empty where conditions in collection.
 * Used from within WhereCondAnd and WhereCondOr.
 * 
 * @author stehlik
 */
class NonEmptyWhereCondCounter implements Consumer<SqlWhereCond> {
        
    private int nonEmptyCount = 0;
        
    public NonEmptyWhereCondCounter() {
    }

    @Override
    public void accept(SqlWhereCond whereCond) {
        if (!whereCond.isEmpty())
            nonEmptyCount++;
    }
        
    public int getNonEmptyCount() {
        return nonEmptyCount;
    }
}

