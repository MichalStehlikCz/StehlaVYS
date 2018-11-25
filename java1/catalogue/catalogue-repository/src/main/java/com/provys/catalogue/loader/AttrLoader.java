/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.loader;

import com.provys.catalogue.model.Attr;
import com.provys.common.confobj.ConfObjectLoader;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import java.util.List;

/**
 * Interface for attribute loader.
 * Apart from generic methods, it supports loading of all attributes of given
 * entity and attribute identified by entity and internal name
 * 
 * @author stehlik
 */
public interface AttrLoader extends ConfObjectLoader<Attr> {
    
    public List<Attr> loadByEntityId(DtUid entityId);
    
    public Attr loadByEntityIdAndNm(DtUid entityId, DtNameNm attrNm);

}
