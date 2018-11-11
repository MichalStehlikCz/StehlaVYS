/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catbuilder;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.sqlbuilder.CodeBuilder;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import java.util.Objects;

/**
 * FROM clause element based on entity from PROVYS catalogue.
 * 
 * @author stehlik
 */
public class CatFromElemEntity implements SqlFromElem {
    
    /**
     * Return from element based on entity.Default alias is lowercase internal
     * name of entity, prefixed with al.No join condition is specified for new
     * from element (resulting in cartesian product in case there already were
     * some from elements).
     * 
     * @param entity is entity new from element will be based on
     * @return from element based on specified entity
     */
    public static CatFromElemEntity of(CatBuilderEntity entity) {
        return new CatFromElemEntity(entity);
    }

    final private CatBuilderEntity entity;
    final private SqlFromElem sqlFromElem;
    
    private CatFromElemEntity(CatBuilderEntity entity)
    {
        Objects.requireNonNull(entity);
        this.sqlFromElem = SqlFromElem.getFromElem(entity.getTable()
                , "al" + entity.getNameNm().getValue().toLowerCase());
        this.entity = entity;
    }

    private CatFromElemEntity(CatBuilderEntity entity, String alias)
    {
        Objects.requireNonNull(entity);
        this.sqlFromElem = SqlFromElem.getFromElem(entity.getTable()
                , alias);
        this.entity = entity;
    }

    /**
     * @return the entity
     */
    public CatBuilderEntity getEntity() {
        return entity;
    }

    @Override
    public void buildSql(CodeBuilder code) {
        sqlFromElem.buildSql(code);
    }

    @Override
    public void buildJoinSql(CodeBuilder code) {
        sqlFromElem.buildJoinSql(code);
    }

    @Override
    public boolean hasJoinSql() {
        return sqlFromElem.hasJoinSql();
    }

    @Override
    public String getAlias() {
        return sqlFromElem.getAlias();
    }

    @Override
    public void setAlias(String alias) {
        sqlFromElem.setAlias(alias);
    }

    @Override
    public String getDefAlias() {
        return "al" + entity.getNameNm().toString().toLowerCase();
    }

}
