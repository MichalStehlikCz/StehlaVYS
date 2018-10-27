/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.datatypes.Dt;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlFromElem;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements simple column, with alias specified as referenced fromElem.
 * Expects that column is single column from fromElem table and places alias
 * from fromElem in front of column - position for alias is not indicated in
 * column text.
 * 
 * @author stehlik
 */
class SqlColumnColumnWithTable extends SqlColumnSimple {
    
    private final SqlFromElem fromElem;
    
    public SqlColumnColumnWithTable(String column, Class<? extends Dt> type
            , SqlFromElem fromElem) {
        super(column, type);
        this.fromElem = fromElem;
    }

    public SqlColumnColumnWithTable(String column, Class<? extends Dt> type
            , boolean indexed, SqlFromElem fromElem) {
        super(column, type, indexed);
        this.fromElem = fromElem;
    }

    public SqlColumnColumnWithTable(String column, String alias
            , Class<? extends Dt> type, SqlFromElem fromElem) {
        super(column, alias, type);
        this.fromElem = fromElem;
    }

    public SqlColumnColumnWithTable(String column, String alias
            , Class<? extends Dt> type, boolean indexed, SqlFromElem fromElem) {
        super(column, alias, type, indexed);
        this.fromElem = fromElem;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        if (fromElem.getAlias() != null) {
            code.append(fromElem.getAlias() + ".");
        }
        code.append(column);
        if (addAliasClause) {
            appendAlias(code);
        }
    }
    
    @Override
    public List<SqlFromElem> getFromElems() {
        List<SqlFromElem> fromElems=new ArrayList<>(1);
        fromElems.add(fromElem);
        return fromElems;
    }
}
