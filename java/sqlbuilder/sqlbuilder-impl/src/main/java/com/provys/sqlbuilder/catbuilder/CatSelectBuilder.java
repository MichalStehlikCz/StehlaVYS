/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catbuilder;

import com.provys.provysdb.call.BindVariable;
import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.iface.SqlColumn;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlOperator2;
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
     * Get column corresponding to attribute.
     * Uses attribute internal name as alias. Checks if there is attr's entity
     * in from clause. If not or if it is there more than once, raises
     * exception.
     * 
     * @param attr is attribute to be added to select
     * @return column with to specified properties
     */
    public CatColumnAttr getAttrColumn(CatBuilderAttr attr);

    /**
     * Get column corresponding to attribute with specified alias.
     * Checks if there is attr's entity in from clause. If not or if it is there
     * more than once, raises exception.
     * 
     * @param attr is attribute to be added to select
     * @param alias is alias assigned to new column
     * @return itself to support chaining
     */
    public CatColumnAttr getAttrColumn(CatBuilderAttr attr, String alias);
    
    /**
     * Get column corresponding to attribute based on specified from element.
     * Uses attribute internal name as alias. Finds table in where clause and
     * verifies that it is attribute's entity. If not, throws an exception.
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attr is attribute to be added to select
     * @return itself to support chaining
     */
    public CatColumnAttr getAttrColumn(String tableAlias
            , CatBuilderAttr attr);

    /**
     * Get column corresponding to attribute with alias based on specified from
     * element.
     * Uses supplied alias as column alias. Finds table in where clause and
     * verifies that it is attribute's entity. If not, throws an exception.
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attr is attribute to be added to select
     * @param alias is alias to be assigned to column
     * @return itself to support chaining
     */
    public CatColumnAttr getAttrColumn(String tableAlias
            , CatBuilderAttr attr, String alias);

    /**
     * Get column corresponding to attribute based on named attribute.
     * Finds table in from clause by alias and finds attribute specified by
     * alias in this entity. Uses attribute internal name as column alias.
     * If tableAlias points to from element other than CatFromElemEntity or
     * if attribute with given internal name doesn't exist in this entity,
     * throws an exception
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attrNm is internal name of attribute to be added to select
     * @return itself to support chaining
     */
    public CatColumnAttr getAttrColumn(String tableAlias
            , String attrNm);

    /**
     * Get column corresponding to named attribute with given alias.
     * Finds table in from clause by alias and finds attribute specified by
     * alias in this entity. Uses supplied alias as column alias.
     * If tableAlias points to from element other than CatFromElemEntity or
     * if attribute with given internal name doesn't exist in this entity,
     * throws an exception
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attrNm is internal name of attribute to be added to select
     * @param alias is alias to be assigned to column
     * @return itself to support chaining
     */
    public CatColumnAttr getAttrColumn(String tableAlias
            , String attrNm, String alias);

    /**
     * Adds column corresponding to attribute to select clause.
     * Uses attribute internal name as alias. Checks if there is attr's entity
     * in from clause. If not or if it is there more than once, raises
     * exception.
     * 
     * @param attr is attribute to be added to select
     * @return itself to support chaining
     */
    public CatSelectBuilder addAttrColumn(CatBuilderAttr attr);

    /**
     * Adds column corresponding to attribute to select clause.
     * Checks if there is attr's entity in from clause. If not or if it is there
     * more than once, raises exception.
     * 
     * @param attr is attribute to be added to select
     * @param alias is alias assigned to new column
     * @return itself to support chaining
     */
    public CatSelectBuilder addAttrColumn(CatBuilderAttr attr, String alias);
    
    /**
     * Adds column corresponding to attribute to select clause.
     * Uses attribute internal name as alias. Finds table in where clause and
     * verifies that it is attribute's entity. If not, throws an exception.
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attr is attribute to be added to select
     * @return itself to support chaining
     */
    public CatSelectBuilder addAttrColumn(String tableAlias
            , CatBuilderAttr attr);

    /**
     * Adds column corresponding to attribute to select clause.
     * Uses supplied alias as column alias. Finds table in where clause and
     * verifies that it is attribute's entity. If not, throws an exception.
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attr is attribute to be added to select
     * @param alias is alias to be assigned to column
     * @return itself to support chaining
     */
    public CatSelectBuilder addAttrColumn(String tableAlias
            , CatBuilderAttr attr, String alias);

    /**
     * Adds column corresponding to attribute to select clause.
     * Finds table in from clause by alias and finds attribute specified by
     * alias in this entity. Uses attribute internal name as column alias.
     * If tableAlias points to from element other than CatFromElemEntity or
     * if attribute with given internal name doesn't exist in this entity,
     * throws an exception
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attrNm is internal name of attribute to be added to select
     * @return itself to support chaining
     */
    public CatSelectBuilder addAttrColumn(String tableAlias
            , String attrNm);

    /**
     * Adds column corresponding to attribute to select clause.
     * Finds table in from clause by alias and finds attribute specified by
     * alias in this entity. Uses supplied alias as column alias.
     * If tableAlias points to from element other than CatFromElemEntity or
     * if attribute with given internal name doesn't exist in this entity,
     * throws an exception
     * 
     * @param tableAlias alias for where clause element to be used for lookup
     * @param attrNm is internal name of attribute to be added to select
     * @param alias is alias to be assigned to column
     * @return itself to support chaining
     */
    public CatSelectBuilder addAttrColumn(String tableAlias
            , String attrNm, String alias);

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
    
    /**
     * Add where condition based on given attribute, operand and bind variable.
     * 
     * @param attr is attribute condition is based on
     * @param operator is operator to be used
     * @param value is bind variable to be compared to
     * @return self to allow chaining
     */
    public CatSelectBuilder addAttrWhereCond(CatBuilderAttr attr
            , SqlOperator2 operator, BindVariable value);

}
