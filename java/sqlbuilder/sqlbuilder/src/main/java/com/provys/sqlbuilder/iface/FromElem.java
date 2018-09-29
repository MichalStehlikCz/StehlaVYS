/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

/**
 * FromElem represents source table in SqlBuilder statement.
 * Source table is kept together with its associated join condition.
 * 
 * @author stehlik
 */
public abstract class FromElem {

    private String alias;
 
    /**
     * Add from clause, associated with this element to supplied CodeBuilder.
     * 
     * @param code is CodeBuilder object used to build SQL statement
     */
    abstract public void buildSql(CodeBuilder code);
    
    /**
     * Add join clause associated with this element to supplied CodeBuilder.
     * Based on PROVYS conventions, join clause is added to WHERE section of
     * SELECT statement, not in FROM section
     * 
     * @param code is CodeBuilder used to build SQL statement
     */
    abstract public void buildJoinSql(CodeBuilder code);
    
    /**
     * Appends alias (if set) to supplied CodeBuilder.
     * 
     * @param code is CodeBuilder used to build SQL statement.
     */
    protected void appendAlias(CodeBuilder code) {
        if (getAlias() != null) {
            code.append(getAlias());
        }
    }

    /**
     * Method indicates if this from element will produce join statement to
     * WHERE clause.
     * 
     * @return whether from element will add join clause to WHERE clause
     */
    abstract public boolean hasJoinSql();
    
    /**
     * Retrieve alias valid for give WHERE element.
     * Even though alias is option in general, based on PROVYS conventions, it
     * should always be used
     * 
     * @return alias used for given statement
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Set alias associated with given element.
     * 
     * @param alias is new alias to be used with given from element.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    /**
     * Returns default alias for given where condition.
     * By default, alias al (possibly appended with number) is used. In case of
     * some specialised types (like entity or table based from clause), system
     * can offer better alias that parses table name or uses entity's internal
     * name.
     * 
     * @return default alias for given from clause.
     */
    public String getDefAlias() {
        return "al";
    }

}
