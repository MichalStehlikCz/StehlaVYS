/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.iface.CodeBuilder;
import com.provys.sqlbuilder.iface.FromElem;

/**
 * Column defined as SQL expression with alias <<0>>.
 * <<0>> is replaced with referenced table alias on use.
 * 
 * @author stehlik
 */
public class SqlColumnSimpleWithTable extends SqlColumnSimple {
    
    private final FromElem fromElem;
    
    public SqlColumnSimpleWithTable(String column, FromElem fromElem) {
        super(column);
        this.fromElem = fromElem;
    }

    @Override
    public void buildSqlNoNewLine(CodeBuilder code, boolean addAliasClause) {
        if (fromElem.getAlias() == null) {
            if (column.contains("<<0>>")) {
                throw new FromAliasMissingException();
            }
        }
        // add identation before newlines
        String sqlText = column.replace("\n", String.format("%1$-"
                + code.getIdent().length() + "s", "\n"));
        // replace table alias
        sqlText = sqlText.replace("<<0>>", fromElem.getAlias());
        // and generate further aliases (doesn't make much sense, but long time
        // ago it seemed to make sense and it is thus used in SQL attributes and
        // queries
        int aliasNum = 1;
        while (sqlText.contains("<<" + aliasNum + ">>")) {
            sqlText = sqlText.replace("<<" + aliasNum + ">>", "al" + aliasNum);
            aliasNum++;
        }
        code.append(sqlText);
        if (addAliasClause) {
            appendAlias(code);
        }
    }

    /**
     * @return the fromElem - table this statement is built on
     */
    public FromElem getFromElem() {
        return fromElem;
    }
    
    /**
     * Cannot build column as referenced from element does not provide alias.
     */
    @SuppressWarnings("PublicInnerClass")
    static public class FromAliasMissingException extends ProvysException {

        private static final long serialVersionUID = 1L;

        FromAliasMissingException() {
            super("From element does not rpovide aliaas needed to build column");
        }
    }
    
}
