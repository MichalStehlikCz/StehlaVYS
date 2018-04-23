/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import java.sql.RowId;

/**
 * Used to pass object together with its ROWID; useful to find deleted objects
 * and remove them
 *
 * @param <T> Object stored together with its ROWID in datastructure
 * @author stehlik
 */
public class ObjectWithRowid<T> {

    private final RowId rowid;
    private final T object;

    public ObjectWithRowid(RowId rowid, T object) {
        this.rowid = rowid;
        this.object = object;
    }

    /**
     * Getter method for rowid
     *
     * @return the rowid
     */
    public RowId getRowid() {
        return rowid;
    }

    /**
     * Getter method for object
     *
     * @return the object
     */
    public T getObject() {
        return object;
    }
}
