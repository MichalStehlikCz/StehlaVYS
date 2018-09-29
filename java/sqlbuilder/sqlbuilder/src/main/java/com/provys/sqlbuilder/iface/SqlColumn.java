/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

/**
 * Represents column in select query.
 * 
 * @author stehlik
 */
abstract public class SqlColumn {
    
    private String alias;
    
    public SqlColumn() {
    }
    
    public SqlColumn(String alias) {
        this.alias = alias;
    }
    
    /**
     * Build SQL code for column (full line).
     * 
     * @param code is CodeBuilder used to build SQL statement
     * @param addAliasClause defines if alias clause should be placed after
     * column code
     */
    public void buildSql(CodeBuilder code, boolean addAliasClause) {
        buildSqlNoNewLine(code, addAliasClause);
        code.appendLine();
    }
    
    /**
     * Build SQL code for column; if possible, build it in line.
     * Note that it is perfectly legal if code is split to multiple lines,
     * for example if column is complete SQL statement. But if possible (e.g.
     * in case it is simple column), it is placed on single line
     * 
     * @param code is CodeBuilder used to build SQL statement
     * @param addAliasClause defines if alias clause should be placed after
     * column code
     */
    abstract public void buildSqlNoNewLine(CodeBuilder code
            , boolean addAliasClause);

    protected void appendAlias(CodeBuilder code) {
        if (getAlias() != null) {
            code.append(" ").append(getAlias());
        }
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

}
