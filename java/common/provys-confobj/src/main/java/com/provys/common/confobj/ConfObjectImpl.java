/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.annotation.ProvysKey;
import com.provys.common.datatypes.DtUid;
import java.io.Serializable;

/**
 * Common ancestor that supports read-only configuration objects. Class supports
 * building in-memory model of configuration and associated functionality. It
 * does not support manipulation with configuration objects, as server logic
 * makes it impossible to keep application server model consistent with database
 *
 * @author stehlik
 */
abstract public class ConfObjectImpl implements ConfObject, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Provys UID.
     * Corresponds to primary key in underlying table
     */
    @ProvysKey
    protected final DtUid id;

    /**
     * Constructor that initializes key to supplied value
     * @param id will be used to initialize Uid value
     */
    public ConfObjectImpl(DtUid id) {
        this.id = id;
    }

    @Override
    public DtUid getId() {
        return this.id;
    }

}
