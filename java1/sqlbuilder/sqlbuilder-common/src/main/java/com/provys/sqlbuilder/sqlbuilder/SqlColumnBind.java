/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;

/**
 * Sql column, containing single bind value.
 * Often used as basis for construction of simple queries using retype.
 * 
 * @author stehlik
 */
class SqlColumnBind extends AbstractSqlColumn {
    
    private final BindVariable bindVariable;

    public static SqlColumnBind of(BindVariable bindVariable) {
        return new SqlColumnBind(bindVariable);
    }
    
    public static SqlColumnBind of(BindVariable bindVariable, String alias) {
        return new SqlColumnBind(bindVariable, alias);
    }
    
    private SqlColumnBind(BindVariable bindVariable) {
        super(bindVariable.getTypeClass(), true);
        this.bindVariable = bindVariable;
    }
    
    private SqlColumnBind(BindVariable bindVariable, String alias) {
        super(alias, bindVariable.getTypeClass(), true);
        this.bindVariable = bindVariable;
    }
    
    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        code.append(":").append(bindVariable.getName()).addBind(bindVariable);
    }

    /**
     * @return the bindVariable
     */
    public BindVariable getBindVariable() {
        return bindVariable;
    }

}
