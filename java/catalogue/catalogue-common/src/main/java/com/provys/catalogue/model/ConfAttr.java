/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.model;

import com.provys.common.confobj.ConfObject;
import com.provys.common.datatypes.*;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
/**
 *
 * @author stehlik
 */
public class ConfAttr extends ConfObject{

    private static final long serialVersionUID = 1L;
    
    private DtUid entityId;
    private DtNameNm nameNm;
    
    @JsonbCreator
    public ConfAttr(@JsonbProperty("ATTR_ID") DtUid id) {
        super(id);
    }
    
    public ConfAttr(DtUid id, DtUid entityId, DtNameNm nameNm){
        super(id);
        this.entityId = entityId;
        this.nameNm = nameNm;
    }

    /**
     * @return the entityId
     */
    public DtUid getEntityId() {
        return entityId;
    }

    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(DtUid entityId) {
        this.entityId = entityId;
    }

    /**
     * @return the nameNm
     */
    public DtNameNm getNameNm() {
        return nameNm;
    }

    /**
     * @param nameNm the nameNm to set
     */
    public void setNameNm(DtNameNm nameNm) {
        this.nameNm = nameNm;
    }
    
}
