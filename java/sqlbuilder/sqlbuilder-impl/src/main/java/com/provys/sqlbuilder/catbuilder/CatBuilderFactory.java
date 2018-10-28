/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catbuilder;

import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.sqlbuilder.SqlBuilderFactory;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import com.provys.sqlbuilder.sqlbuilder.SqlQueryBuilder;

/**
 * Methods to create elements, columns and statements based on entity catalogue.
 * 
 * @author stehlik
 */
public interface CatBuilderFactory extends SqlBuilderFactory {

    /**
     * Creates new SqlSelectBuilder based on specified entity.
     * 
     * @param entity specified PROVYS catalogue entity new CatSelectBuilder
     * should use
     * @return new instance of CatSelectBuilder based on specified entity
     */
    public CatSelectBuilder getSelectBuilder(CatBuilderEntity entity);
    
    /**
     * Creates SqlQueryBuilder based on given entity.
     * 
     * @param entity is used as bases (added to from clause, its key added to
     * columns)
     * @return new SqlQueryBuilder built on specified entity
     */
    public SqlQueryBuilder getQueryBuilder(CatBuilderEntity entity);
    
    /**
     * Creates SqlFromElem based on specified entity.
     * 
     * @param entity new FROM element will be based on
     * @return SqlFromElem based on specified entity
     */
    public SqlFromElem getFromElem(CatBuilderEntity entity);
    
}
