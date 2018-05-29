/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.manager;

import com.provys.catalogue.dbloader.ConfAttrLoaderDb;
import com.provys.catalogue.dbloader.ConfAttrLoaderDb;
import javax.ejb.Singleton;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import com.provys.catalogue.model.ConfAttr;
import com.provys.common.confobj.*;
import com.provys.common.datatypes.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.provys.catalogue.iface.ConfAttrManager;

/**
 *
 * @author stehlik
 */
@Local
@Singleton
@ApplicationScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Named("ConfAttrManager")
public class ConfAttrManagerLocal extends ConfObjectManager<ConfAttr> implements ConfAttrManager {

    /**
     *
     * @author stehlik
     * 
     * Used to notify EntityManager about load of new attribute(s)
     *
    public class AttrCreated {
        
        /**
         * Array of created attributes 
         *
        final private ConfAttr[] attrId;
        
        public AttrCreated(ConfAttr[] attrId){
            this.attrId = attrId;
        }
        
        public ConfAttr[] getConfAttr() {
            return attrId;
        }
    }
    
    public class EntityChanged {
        DtUid oldEntityId;
        DtUid newEntityId;
        
        public EntityChanged(DtUid oldEntityId, DtUid newEntityId){
            this.oldEntityId = oldEntityId;
            this.newEntityId = newEntityId;
        }
    }
  */
  
    @Inject
    private ConfAttrLoaderDb confAttrLoader;

    @Override
    protected ConfAttrLoaderDb getConfObjectLoader(){
        return confAttrLoader;
    }

    public Map<DtNameNm, ConfAttr> loadByEntityId(DtUid entityId){
        Map<DtNameNm, ConfAttr> result = new ConcurrentHashMap<>();
        getConfObjectLoader().loadByEntityId(entityId)
                .forEach((RowidObjectPair<ConfAttr> confAttrWithRowid) -> {
                    ConfAttr confAttr = add(confAttrWithRowid);
                    if (confAttr == null) {
                        result.put(confAttrWithRowid.getObject().getNameNm()
                                , confAttrWithRowid.getObject());
                    } else {
                        result.put(confAttr.getNameNm(), confAttr);
                    }
        });
        return result;
    }

}
