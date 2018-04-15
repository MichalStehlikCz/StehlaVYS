/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.annotation.*;
import com.provys.common.datatypes.*;

/**
 *
 * @author stehlik
 */
abstract public class ConfNMObject extends ConfObject {

    private static final long serialVersionUID = 1L;

    @ProvysAttr("NAME_NM")
    private DtNameNm nameNm;

    public ConfNMObject(DtUid id, DtNameNm nameNm){
        super(id);
        this.nameNm=nameNm;
    }
    
    
    public synchronized DtNameNm getNameNm(){
        return this.nameNm;
    }
    
    public synchronized void setNameNm(DtNameNm nameNm){
        this.nameNm=nameNm;
    }
}
