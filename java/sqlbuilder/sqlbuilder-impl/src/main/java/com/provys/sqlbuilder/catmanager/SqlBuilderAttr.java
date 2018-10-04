/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfObject;

/**
 * Interface defines methods needed by SqlBuilder from attribute implementation.
 * 
 * @author stehlik
 */
public interface SqlBuilderAttr extends ConfObject {
    public String getSql(boolean useView);
    public boolean needsTable();
}
