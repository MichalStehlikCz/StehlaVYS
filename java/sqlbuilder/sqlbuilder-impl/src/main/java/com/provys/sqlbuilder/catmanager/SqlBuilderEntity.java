/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfNMObject;

/**
 * Interface defines contract needed by SqlBuilder from Entity implementation.
 * 
 * @author stehlik
 */
public interface SqlBuilderEntity extends ConfNMObject {
    public String getTable();
    public String getKey();
}
