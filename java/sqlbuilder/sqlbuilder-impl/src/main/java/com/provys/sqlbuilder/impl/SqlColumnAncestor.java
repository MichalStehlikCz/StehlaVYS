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

/**
 * Represents column in select query.
 * 
 * @author stehlik
 */
abstract public class SqlColumnAncestor implements SqlColumn {
    
    private String alias;
    private String type;
    private int size = -1;
    private boolean indexed = false;
    
    public SqlColumnAncestor() {
    }
    
    public SqlColumnAncestor(String alias, Class<? extends Dt> type) {
        this.alias = alias;
        this.type = type.getSimpleName();
    }
    
    public SqlColumnAncestor(String alias, Class<? extends Dt> type
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
        if ((this.type != null) & (this.alias != null)) {
            ColumnDef result = new ColumnDef();
            result.setType(this.type);
            result.setName(this.alias);
            return result;
        }
        return null;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Setter method for type.
     * Method finds corresponding class and calls setType method with class
     * parameter
     * 
     * @param type sets type field
     */
    public void setType(String type) {
        Class<? extends Dt> typeClass;
        try {
            typeClass = Class
                    .forName("com.provys.common.datatypes"+type)
                    .asSubclass(Dt.class);
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedTypeException(type, ex);
        }
        setType(typeClass);
    }
    
    /**
     * Set type of column to correspond to supplied class
     * @param type is class column should be stored in
     */
    public void setType(Class<? extends Dt> type) {
        this.type = type.getSimpleName();
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

    @Override
    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Exception raised when value supplied to ColumnDef is not one of supported
     * types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedColumnDatatypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedColumnDatatypeException(String type) {
            super("Unsupported class for column definition: "+type);
        }
    }

    /**
     * Exception raised when type supplied to ColumnDef (as string) is not one
     * of supported types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedTypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedTypeException(String type, Throwable cause) {
            super("Unsupported type for column definition: "+type, cause);
        }
    }
}