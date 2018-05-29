/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.model;

import com.provys.common.confobj.ConfNMObject;
import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbConfEntityAdapter.class)
public class ConfEntity extends ConfNMObject{

    private static final long serialVersionUID = 1L;

    private Map<DtNameNm, ConfAttr> attrMap = null;
    
    public ConfEntity(DtUid id, DtNameNm nameNm){
        super(id, nameNm);
    }

    public Map<DtNameNm, ConfAttr> getAttrMap() {
        Map<DtNameNm, ConfAttr> result = null;
        if (attrMap != null) {
            result = new ConcurrentHashMap<>(attrMap.size());
            result.putAll(attrMap);
        }
        return result;
    }
    
    public Collection<ConfAttr> getAttrs() {
        return attrMap.values();
    }

    public Map<DtNameNm, ConfAttr> getAttrMapRef() {
        return attrMap;
    }
    
    public synchronized void setAttrMap(Map<DtNameNm, ConfAttr> attrMap){
        this.attrMap=attrMap;
    }

    public ConfAttr getAttrByNm(DtNameNm attrNm) {
        if (attrMap == null) {
            throw new CannotGetAttrNotLoadedException();
        }
        return attrMap.get(attrNm);
    }

    @SuppressWarnings("PublicInnerClass")
    public class CannotGetAttrNotLoadedException extends ProvysException {

        private static final long serialVersionUID = 1L;
        public CannotGetAttrNotLoadedException() {
            super("Cannot lookup attr - attrs not loaded");
        }
    }

}
