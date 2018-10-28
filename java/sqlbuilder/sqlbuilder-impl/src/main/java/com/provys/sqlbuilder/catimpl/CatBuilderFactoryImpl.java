/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.sqlbuilder.catbuilder.CatSelectBuilder;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.sqlbuilder.SqlFromElem;
import com.provys.sqlbuilder.sqlbuilder.SqlQueryBuilder;
import com.provys.sqlbuilder.sqlbuilder.QueryBuilderSimple;
import com.provys.sqlbuilder.sqlbuilder.SqlBuilderFactoryImpl;
import com.provys.sqlbuilder.catbuilder.CatBuilderFactory;
import com.provys.sqlbuilder.sqlbuilder.SqlColumn;

/**
 * Factory class for catalogue related SqlBuilder objects.
 * 
 * @author stehlik
 */
public class CatBuilderFactoryImpl implements CatBuilderFactory {
    
    @Override
    public CatSelectBuilder getSelectBuilder(CatBuilderEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlQueryBuilder getQueryBuilder(CatBuilderEntity entity) {
        return new QueryBuilderSimple(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SqlFromElem getFromElem(CatBuilderEntity entity) {
        return new CatFromElemEntity(entity);
    }

}
