/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;

/**
 * Interface exposing basic configuration object repository.
 * 
 * @author stehlik
 * 
 * @param <T> Configuration object class this repository holds
 */
public interface ConfObjectRepository<T extends ConfObject> {

    T get(DtUid id);

    T getExisting(DtUid id);

    void load(DtUid id);
    
}
