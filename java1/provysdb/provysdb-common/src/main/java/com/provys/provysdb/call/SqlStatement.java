/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import java.util.List;
import java.util.Optional;

/**
 * SqlStatement represents statement (usually SELECT) created from builder.
 * Values for bind variables have to be supplied in order to build executable
 * SqlCall
 * 
 * @author stehlik
 */
public class SqlStatement extends SqlAncestor<BindVariable> {

    public SqlStatement(String sql, List<BindVariable> variables
            , List<ColumnDef> columns) {
        super(sql, variables, columns);
    }

    public SqlStatement(String sql, Optional<List<BindVariable>> variables
            , Optional<List<ColumnDef>> columns) {
        super(sql, variables, columns);
    }
    
}
