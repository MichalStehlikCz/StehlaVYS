/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.common.error.ProvysException;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElemSimple;

/**
 * FROM clause element based on entity from PROVYS catalogue.
 * 
 * @author stehlik
 */
public class CatFromElemEntity extends SqlFromElemSimple implements SqlFromElem {
    
    final private CatBuilderEntity entity;
    
    public CatFromElemEntity(CatBuilderEntity entity) {
        super(entity.getTable());
        this.entity = entity;
    }

    /**
     * @return the entity
     */
    public CatBuilderEntity getEntity() {
        return entity;
    }

    @Override
    public String getDefAlias() {
        if (entity == null) {
            throw new EntityNotSpecifiedException();
        }
        return "al" + entity.getNameNm().toString().toLowerCase();
    }

    /**
     * Exception raised when trying to using functions needing entity on
 SqlFromElem that has no entity
     */
    @SuppressWarnings("PublicInnerClass")
    static public class EntityNotSpecifiedException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        EntityNotSpecifiedException() {
            super("FromElemEntity has no entity specified");
        }
    }
}
