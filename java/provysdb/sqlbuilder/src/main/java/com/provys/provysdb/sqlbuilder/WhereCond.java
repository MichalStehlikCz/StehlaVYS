/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlbuilder;

import com.provys.provysdb.api.BindValue;
import java.util.List;

/**
 * Represents WHERE condition in SQL builder.
 * Different implementations allow to create WHERE conditions based on
 * supplied string, application of unary or binary operator on attribute
 * or on supplied subquery
 * 
 * @author stehlik
 */
public interface WhereCond {
    public String getWhere(String alias, List<BindValue> binds);
    public int getCost();
    
}
