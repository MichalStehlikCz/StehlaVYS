/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;

/**
 * Common functionality provided by any configuration object.
 * Configuration objects are in-memory read-only representations of PROVYS
 * objects.
 *
 * @author stehlik
 */
public interface ConfObject {

    /**
     * Simple getter method for id
     * @return value of key (uid)
     */
    public DtUid getId();

}
