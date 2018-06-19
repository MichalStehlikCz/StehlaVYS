/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.iface;

import com.provys.catalogue.model.ConfAttr;
import com.provys.catalogue.model.ConfEntity;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import javax.ejb.Local;

/**
 *
 * @author stehlik
 */
@Local
public interface ConfEntityManager {

    ConfEntity get(DtUid id);

    ConfEntity getByNm(DtNameNm nameNm);

    void load(DtUid id);

    void loadByNm(DtNameNm nameNm);
    
    public ConfAttr getAttrByNm(DtUid entityId, DtNameNm attrNm);
    
    default public ConfAttr getAttrByNm(DtNameNm nameNm, DtNameNm attrNm) {
        return getAttrByNm(getByNm(nameNm).getId(), attrNm);
    };
    
    public void loadAttrs(DtUid entityId);

    default public void loadAttrsByNm(DtNameNm nameNm) {
        loadAttrs(getByNm(nameNm).getId());
    }

}
