/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.repository;

import com.provys.catalogue.iface.AttrRepository;
import com.provys.catalogue.iface.CatalogueManager;
import com.provys.catalogue.loader.AttrLoader;
import com.provys.catalogue.model.Attr;
import com.provys.common.confobj.ConfObjectRepositoryImpl;
import com.provys.common.datatypes.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation class for Attr repository.
 * 
 * @author stehlik
 */

public class AttrRepositoryImpl extends ConfObjectRepositoryImpl<Attr>
        implements AttrRepository {

    @Inject
    private AttrLoader attrLoader;

    private final CatalogueManager catalogueManager;
    
    public AttrRepositoryImpl(CatalogueManager catalogueManager) {
        this.catalogueManager = catalogueManager;
    }
            
    @Override
    protected AttrLoader getConfObjectLoader(){
        return attrLoader;
    }

    /**
     * Loads attributes of given entity that were not loaded yet and returns
     * list of all attributes of given entity. In return value, even attributes
     * that were already loaded previously are included.
     * 
     * @param entityId
     * @return list of all attributes of specified entity
     */
    @Override
    public List<Attr> loadByEntityId(DtUid entityId){
        List<Attr> result = new ArrayList<>();
        getConfObjectLoader().loadByEntityId(entityId)
                .forEach((Attr attr) -> {
                    Attr oldAttr = add(attr);
                    if (oldAttr == null) {
                        result.add(attr);
                    } else {
                        result.add(oldAttr);
                    }
        });
        return result;
    }

}
