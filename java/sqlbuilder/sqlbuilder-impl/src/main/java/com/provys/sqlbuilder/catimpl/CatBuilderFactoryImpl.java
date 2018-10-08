/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.sqlbuilder.catbuilder.CatSelectBuilder;
import com.provys.sqlbuilder.catbuilder.CatlBuilderFactory;
import com.provys.sqlbuilder.catmanager.CatBuilderEntity;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.iface.SqlQueryBuilder;
import com.provys.sqlbuilder.iface.SqlSelectBuilder;
import com.provys.sqlbuilder.impl.QueryBuilderSimple;
import com.provys.sqlbuilder.impl.SqlBuilderFactoryImpl;

/**
 * Factory class for catalogue related SqlBuilder objects.
 * 
 * @author stehlik
 */
public class CatBuilderFactoryImpl
        extends SqlBuilderFactoryImpl implements CatlBuilderFactory {
    
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
