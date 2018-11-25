/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;

/**
 * Represents column in select query.
 * Common ancestor for classes, implementing SqlColumn interface. Keeps alias,
 * type and indexed flag and implements functions that depend on these common
 * properties.
 * 
 * @author stehlik
 */
abstract public class AbstractSqlColumn implements SqlColumn {
    
    private final String alias;
    private final String type;
    private final boolean indexed;
    
    public AbstractSqlColumn(Class<? extends Dt> type) {
        this.alias = null;
        this.type = type.getSimpleName();
        this.indexed = false;
    }
    
    public AbstractSqlColumn(Class<? extends Dt> type, boolean indexed) {
        this.alias = null;
        this.type = type.getSimpleName();
        this.indexed = indexed;
    }
    
    public AbstractSqlColumn(String alias, Class<? extends Dt> type) {
        this.alias = alias;
        this.type = type.getSimpleName();
        this.indexed = false;
    }
    
    public AbstractSqlColumn(String alias, Class<? extends Dt> type
            , boolean indexed) {
        this.alias = alias;
        this.type = type.getSimpleName();
        this.indexed = indexed;
    }
    
    @Override
    public void buildSql(CodeBuilder code, boolean addAliasClause) {
        buildSqlNoNewLine(code, addAliasClause);
        code.appendLine();
    }
    
    protected void appendAlias(CodeBuilder code) {
        if (getAlias() != null) {
            code.append(" ").append(getAlias());
        }
    }

    @Override
    public ColumnDef getColumnDef() {
        if (this.alias == null) {
            throw new ColumnAliasMissingException();
        }
        if (this.type == null) {
            throw new ColumnTypeMissingException();
        }
        ColumnDef result = new ColumnDef(this.alias, this.type);
        return result;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    @Override
    public boolean isIndexed() {
        return indexed;
    }

    /**
     * Exception raised when trying to get column definition and alias is not
     * specified for column
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnAliasMissingException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        ColumnAliasMissingException() {
            super("Cannot build column definition - alias is not specified");
        }
    }

    /**
     * Exception raised when trying to get column definition and type is not
     * specified for column
     */
    @SuppressWarnings("PublicInnerClass")
    static public class ColumnTypeMissingException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        ColumnTypeMissingException() {
            super("Cannot build column definition - type is not specified");
        }
    }
}