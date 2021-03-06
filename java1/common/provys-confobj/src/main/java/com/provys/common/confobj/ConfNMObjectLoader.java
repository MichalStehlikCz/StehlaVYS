/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtNameNm;

/**
 * Configuration object loader extension for objects with internal name.
 * This extension allows to load object specified by internal name instead of
 * Id. This allows some optimisation, as very often objects are identified by
 * internal name instead of Id.
 *
 * @author stehlik
 * @param <T> Configuration object class this loader retrieves from database
 * 
*/
public interface ConfNMObjectLoader<T extends ConfNMObject>
        extends ConfObjectLoader<T> {

    public T loadByNm(DtNameNm nameNm);
}
