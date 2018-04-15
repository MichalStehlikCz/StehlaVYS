/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author stehlik
 * @param <T> Configuration object class this loader retrieves from database
 * 
 */
public abstract class ConfObjectLoader<T extends ConfObject> {
    
    @Resource(lookup = "jdbc/provysDB")
    protected DataSource provysDB;

    public abstract ObjectWithRowid<T> load(DtUid id);
    
}
