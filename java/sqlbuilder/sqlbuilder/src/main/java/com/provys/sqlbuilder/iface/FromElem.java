/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

/**
 *
 * @author stehlik
 */
public abstract class FromElem {

    private String alias;
 
    abstract public void buildSql(CodeBuilder code);
    public String getAlias() {
        return alias;
    }

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
