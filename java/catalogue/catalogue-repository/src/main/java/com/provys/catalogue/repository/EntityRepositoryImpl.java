/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.repository;

import com.provys.catalogue.iface.EntityRepository;
import com.provys.catalogue.model.Attr;
import com.provys.catalogue.model.Entity;
import com.provys.common.confobj.ConfNMObjectRepository;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import javax.inject.Inject;

/**
 *
 * @author stehlik
 */
public class EntityRepositoryImpl extends ConfNMObjectRepository<Entity>
        implements EntityRepository {

    @Inject
    private EntityLoader entityLoader;
    
    @Override
    protected EntityLoader getConfObjectLoader(){
        return entityLoader;
    }
    
    private Attr getAttrByNm(Entity entity, DtNameNm attrNm) {
        loadAttrs(entity);
        return entity.getAttrByNm(attrNm);
    }
    
    @Override
    public ConfAttr getAttrByNm(DtUid entityId, DtNameNm attrNm) {
        return getAttrByNm(get(entityId), attrNm);
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

    @Override
    public Attr getAttrByNm(DtUid entityId, DtNameNm attrNm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadAttrs(DtUid entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
