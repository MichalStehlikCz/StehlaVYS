/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.*;

/**
 *
 * @author stehlik
 * @param <T> Configuration object class this loader retrieves from database
 * 
*/
abstract public class ConfNMObjectLoader<T extends ConfNMObject> extends ConfObjectLoader<T> {

    abstract public RowidObjectPair<T> loadByNm(DtNameNm nameNm);
}
