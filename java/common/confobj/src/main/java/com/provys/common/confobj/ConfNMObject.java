/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.*;
import com.provys.common.annotation.*;
import javax.json.bind.annotation.*;
/**
 *
 * @author stehlik
 */
abstract public class ConfNMObject extends ConfObject {

    @JsonbProperty("NAME_NM")
    @ProvysAttr("NAME_NM")
    private DtNameNm nameNm;

    public ConfNMObject(DtUid id, DtNameNm nameNm){
        super(id);
        this.nameNm=nameNm;
    }
    
    
    public DtNameNm getNameNm(){
        return this.nameNm;
    }
    
    public synchronized void setNameNm(DtNameNm nameNm){
        this.nameNm=nameNm;
    }
}
