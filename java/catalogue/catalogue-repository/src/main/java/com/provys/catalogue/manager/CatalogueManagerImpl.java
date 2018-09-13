/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.manager;

import com.provys.catalogue.iface.AttrRepository;
import com.provys.catalogue.iface.CatalogueManager;
import com.provys.catalogue.iface.EntityRepository;
import com.provys.catalogue.repository.AttrRepositoryImpl;
import com.provys.catalogue.repository.EntityRepositoryImpl;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author stehlik
 */
@ApplicationScoped
public class CatalogueManagerImpl implements CatalogueManager {

    EntityRepository entityRepository;
    AttrRepository attrRepository;
    
    public CatalogueManagerImpl() {
        entityRepository = new EntityRepositoryImpl(this);
        attrRepository = new AttrRepositoryImpl(this);
    }

    @Override
    public EntityRepository getEntityRepository() {
        return entityRepository;
    }

    @Override
    public AttrRepository getAttrRepository() {
        return attrRepository;
    }
    
}
