/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.model;

import com.provys.common.confobj.ConfNMObjectImpl;
import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.json.bind.annotation.JsonbTypeAdapter;

/**
 *
 * @author stehlik
 */
@JsonbTypeAdapter(JsonbEntityAdapter.class)
public class Entity extends ConfNMObjectImpl {

    private static final long serialVersionUID = 1L;

    private Map<DtNameNm, Attr> attrMap = null;
    
    public Entity(DtUid id, DtNameNm nameNm){
        super(id, nameNm);
    }

    public Map<DtNameNm, Attr> getAttrMap() {
        if (attrMap == null) {
            throw new CannotGetAttrNotLoadedException();
        }
        return Collections.unmodifiableMap(attrMap);
    }
    
    public synchronized void setAttrMap(Map<DtNameNm, Attr> attrMap){
        this.attrMap=new ConcurrentHashMap<>(attrMap);
    }

    public synchronized void setAttrMap(List<Attr> attrList){
        this.attrMap=new ConcurrentHashMap<>(attrList.size());
        attrList.forEach((Attr attr) -> {attrMap.put(attr.getNameNm(), attr);});
    }

    public Collection<Attr> getAttrs() {
        if (attrMap == null) {
            throw new CannotGetAttrNotLoadedException();
        }
        return attrMap.values();
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public Map<DtNameNm, Attr> getAttrMapRef() {
        return attrMap;
    }
    
    public Attr getAttrByNm(DtNameNm attrNm) {
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
