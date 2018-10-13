/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtNameNm;

/**
 * Interface for repository holding configuration objects, identified by
 * internal name.
 * 
 * @author stehlik
 * @param <T> Configuration object class this interface holds
 */
public interface ConfNMObjectRepository<T extends ConfNMObject>
        extends ConfObjectRepository<T> {

    T getByNm(DtNameNm nameNm);

    T getExistingByNm(DtNameNm nameNm);

    void loadByNm(DtNameNm nameNm);
    
}
