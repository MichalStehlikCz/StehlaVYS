/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.repository;

import com.provys.catalogue.iface.CatalogueManager;
import com.provys.catalogue.iface.EntityRepository;
import com.provys.catalogue.loader.EntityLoader;
import com.provys.catalogue.model.Attr;
import com.provys.catalogue.model.Entity;
import com.provys.common.confobj.ConfNMObjectRepositoryImpl;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import javax.inject.Inject;

/**
 *
 * @author stehlik
 */
public class EntityRepositoryImpl extends ConfNMObjectRepositoryImpl<Entity>
        implements EntityRepository {

    @Inject
    private EntityLoader entityLoader;
    private final CatalogueManager catalogueManager;
    
    EntityRepositoryImpl(CatalogueManager catalogueManager) {
        this.catalogueManager = catalogueManager;
    }
            
    @Override
    protected EntityLoader getConfObjectLoader(){
        return entityLoader;
    }
    
    private Attr getAttrByNm(Entity entity, DtNameNm attrNm) {
        loadAttrs(entity);
        return entity.getAttrByNm(attrNm);
    }
    
    @Override
    public Attr getAttrByNm(DtUid entityId, DtNameNm attrNm) {
        return getAttrByNm(get(entityId), attrNm);
    }
    
    private void loadAttrs(Entity confEntity) {
        if (confEntity.getAttrMapRef() == null) {
            confEntity.setAttrMap(catalogueManager.getAttrRepository()
                    .loadByEntityId(confEntity.getId()));
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
