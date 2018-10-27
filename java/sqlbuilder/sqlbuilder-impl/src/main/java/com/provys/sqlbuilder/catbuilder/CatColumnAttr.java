/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catbuilder;

import com.provys.sqlbuilder.catmanager.CatBuilderAttr;
import com.provys.sqlbuilder.iface.SqlColumn;

/**
 * Extends {@code SqlColumn} interface by adding access to attribute.
 * 
 * @author stehlik
 */
public interface CatColumnAttr extends SqlColumn {
    
    /**
     * Access to attribute this column is built on.
     * 
     * @return attribute this column is based on 
     */
    public CatBuilderAttr getAttr();
}
