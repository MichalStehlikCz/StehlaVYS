/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;

/**
 * Represents simple column, specified as text, without alias.
 * 
 * @author stehlik
 */
class SqlColumnSimple extends AbstractSqlColumn {
    
    final String column; // text representing table column
    
    public SqlColumnSimple(String column, Class<? extends Dt> type) {
        super(type);
        this.column = column;
    }

    public SqlColumnSimple(String column, Class<? extends Dt> type
            , boolean indexed) {
        super(type, indexed);
        this.column = column;
    }

    public SqlColumnSimple(String column, String alias
            , Class<? extends Dt> type) {
        super(alias, type);
        this.column = column;
    }

    public SqlColumnSimple(String column, String alias
            , Class<? extends Dt> type, boolean indexed) {
        super(alias, type, indexed);
        this.column = column;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        code.append(column);
        if (addAliasClause) {
            appendAlias(code);
        }
    }

}
