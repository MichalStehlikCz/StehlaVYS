/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import java.util.ArrayList;
import java.util.List;

/**
 * Column defined as SQL expression with alias <<0>>.
 * <<0>> is replaced with referenced table alias on use.
 * 
 * @author stehlik
 */
class SqlColumnSimpleWithTable implements SqlColumn {
    
    private final SqlColumnSimple sqlColumn;
    private final SqlFromElem fromElem;
    
    public SqlColumnSimpleWithTable(String column, Class<? extends Dt> type
            , SqlFromElem fromElem) {
        super(column, type);
        this.fromElem = fromElem;
    }

    public SqlColumnSimpleWithTable(String column, Class<? extends Dt> type
            , boolean indexed, SqlFromElem fromElem) {
        super(column, type, indexed);
        this.fromElem = fromElem;
    }

    private SqlColumnSimpleWithTable(String column, String alias
            , Class<? extends Dt> type, SqlFromElem fromElem) {
        super(column, alias, type);
        this.fromElem = fromElem;
    }

    private SqlColumnSimpleWithTable(SqlFromElem fromElem, String column
            , String alias, Class<? extends Dt> type, boolean indexed) {
        super(column, alias, type, indexed);
        this.fromElem = fromElem;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        if (fromElem.getAlias() == null) {
            if (column.contains("<<0>>")) {
                throw new FromAliasMissingException();
            }
        }
        // replace table alias
        String sqlText = column.replace("<<0>>", fromElem.getAlias());
        // and generate further aliases (doesn't make much sense, but long time
        // ago it seemed to make sense and it is thus used in SQL attributes and
        // queries
        int aliasNum = 1;
        while (sqlText.contains("<<" + aliasNum + ">>")) {
            sqlText = sqlText.replace("<<" + aliasNum + ">>", "al" + aliasNum);
            aliasNum++;
        }
        code.appendWrapped(sqlText);
        if (addAliasClause) {
            appendAlias(code);
        }
    }

    /**
     * @return the fromElem - table this statement is built on
     */
    public SqlFromElem getFromElem() {
        return fromElem;
    }
    
    @Override
    public List<SqlFromElem> getFromElems() {
        List<SqlFromElem> fromElems=new ArrayList<>(1);
        fromElems.add(fromElem);
        return fromElems;
    }

    @Override
    public void buildSql(CodeBuilder code, boolean addAliasClause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ColumnDef getColumnDef() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAlias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIndexed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Cannot build column as referenced from element does not provide alias.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class FromAliasMissingException extends ProvysException {

        private static final long serialVersionUID = 1L;

        FromAliasMissingException() {
            super("From element does not provide alias needed to build column");
        }
    }
    
}
