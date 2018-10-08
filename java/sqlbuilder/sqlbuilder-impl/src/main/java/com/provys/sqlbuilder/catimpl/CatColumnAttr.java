/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catimpl;

import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.iface.SqlFromElem;
import com.provys.sqlbuilder.impl.SqlColumnSimpleWithTable;

/**
 * Class represents column defined by attribute.
 * It is related to table in from clause.
 * 
 * @author stehlik
 */
public class CatColumnAttr extends SqlColumnSimpleWithTable {
    
    private final CatBuilderAttr attr;

    public CatColumnAttr(CatBuilderAttr attr, SqlFromElem fromElem) {
        super(attr.getSql(false), attr.getNm().toString().toLowerCase()
                , attr.getTypeClass(), attr.isIndexed(), fromElem);
        this.attr = attr;
    }

    public CatColumnAttr(CatBuilderAttr attr, String alias, SqlFromElem fromElem) {
        super(attr.getSql(false), alias, attr.getTypeClass(), attr.isIndexed()
                , fromElem);
        this.attr = attr;
    }
}
