/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.annotation.*;
import com.provys.common.datatypes.*;

/**
 * Default implementation of ConfNMObject interface.
 * Makes it easier to implement ancestors.
 * 
 * @author stehlik
 */
abstract public class ConfNMObjectImpl extends ConfObjectImpl
        implements ConfNMObject {

    private static final long serialVersionUID = 1L;

    @ProvysAttr("NAME_NM")
    private DtNameNm nameNm;

    public ConfNMObjectImpl(DtUid id, DtNameNm nameNm){
        super(id);
        this.nameNm=nameNm;
    }
    
    @Override
    public synchronized DtNameNm getNameNm(){
        return this.nameNm;
    }
    
    @Override
    public synchronized void setNameNm(DtNameNm nameNm){
        this.nameNm=nameNm;
    }
}