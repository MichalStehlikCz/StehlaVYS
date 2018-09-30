/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtNameNm;

/**
 * Interface defines functionality for configuration object, identified by
 * internal name.
 * 
 * @author stehlik
 */
public interface ConfNMObject extends ConfObject {

    public DtNameNm getNameNm();
    
    public void setNameNm(DtNameNm nameNm);
}
