/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catbuilder;

import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlSelectBuilder;

/**
 * Extends SqlSelectBuilder functionality with catalogue specific functions.
 * 
 * @author stehlik
 */
public interface CatSelectBuilder extends SqlSelectBuilder {
    
    @Override
    public CatSelectBuilder addColumn(SqlColumn column);
    
    /**
     * Adds column corresponding to attribute to select clause.
     * Uses attribute internal name as alias. Checks if there is attr's entity
     * in from clause. If not or if it is there more than once, raises
     * exception.
     * 
     * @param attr is attribute to be added to select
     * @return itself to support chaining
     */
    public CatSelectBuilder addColumn(CatBuilderAttr attr);

    /**
     * Adds column corresponding to attribute to select clause.
     * Checks if there is attr's entity in from clause. If not or if it is there
     * more than once, raises exception.
     * 
     * @param attr is attribute to be added to select
     * @param alias is alias assigned to new column
     * @return itself to support chaining
     */
    public CatSelectBuilder addColumn(CatBuilderAttr attr, String alias);
    
    /**
     * Get FROM element built on specified entity.
     * Throw exception if from element is not found or there is more than one.
     * Should be used with caution on dynamically build selects, as there is
     * serious risk of adding join to same entity and destroying unrelated
     * code, on the other hand it might simplify code when only simple select
     * is created
     * 
     * @param entity is entity being looked up
     * @return FromElem build on specified entity
     */
    public SqlFromElem getFromElemByEntity(CatBuilderEntity entity);
}
