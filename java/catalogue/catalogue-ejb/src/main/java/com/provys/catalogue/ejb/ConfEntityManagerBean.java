/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.ejb;

import javax.ejb.Singleton;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import com.provys.catalogue.model.ConfEntity;
import com.provys.catalogue.model.ConfAttr;
import com.provys.common.confobj.*;
import com.provys.common.datatypes.*;
import com.provys.catalogue.iface.ConfEntityManagerBeanLocal;

/**
 *
 * @author stehlik
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ConfEntityManagerBean extends ConfNMObjectManager<ConfEntity> implements ConfEntityManagerBeanLocal {

    @EJB
    private ConfEntityLoaderBean entityLoader;
    
    @EJB
    private ConfAttrManagerBean attrManager;

    @Override
    protected ConfEntityLoaderBean getConfObjectLoader(){
        return entityLoader;
    }
    
    private ConfAttr getAttrByNm(ConfEntity confEntity, DtNameNm attrNm) {
        loadAttrs(confEntity);
        return confEntity.getAttrByNm(attrNm);
    }
    
    @Override
    public ConfAttr getAttrByNm(DtUid entityId, DtNameNm attrNm) {
        return getAttrByNm(get(entityId), attrNm);
    }
    
    @Override
    public ConfAttr getAttrByNm(DtNameNm nameNm, DtNameNm attrNm) {
        return getAttrByNm(getByNm(nameNm), attrNm);
    }
    
    private void loadAttrs(ConfEntity confEntity) {
        if (confEntity.getAttrMapRef() == null) {
            confEntity.setAttrMap(attrManager.loadByEntityId(confEntity.getId()));
        }
    }

    @Override
    public void loadAttrs(DtUid entityId) {
        loadAttrs(get(entityId));
    }

    @Override
    public void loadAttrsByNm(DtNameNm nameNm) {
        loadAttrs(getByNm(nameNm));
    }

}
