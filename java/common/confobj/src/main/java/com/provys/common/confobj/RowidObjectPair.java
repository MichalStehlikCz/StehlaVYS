/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import oracle.sql.ROWID;

/**
 *
 * @author stehlik
 * 
 * Used to pass object together with its ROWID; useful to find deleted objects
 * and remove them
 * @param <T> Object stored together with its ROWID in datastructure
 */
public class RowidObjectPair<T> {
    private final ROWID rowid;
    private final T object;
    
    public RowidObjectPair(ROWID rowid, T object) {
        this.rowid = rowid;
        this.object = object;
    }

    /**
     * @return the rowid
     */
    public ROWID getRowid() {
        return rowid;
    }

    /**
     * @return the object
     */
    public T getObject() {
        return object;
    }
}
