/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.repository;

import com.provys.catalogue.iface.AttrRepository;
import com.provys.catalogue.loader.AttrLoader;
import com.provys.catalogue.model.Attr;
import com.provys.common.confobj.ConfObjectRepositoryImpl;
import com.provys.common.datatypes.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;

/**
 *
 * @author stehlik
 */

public class AttrRepositoryImpl extends ConfObjectRepositoryImpl<Attr>
        implements AttrRepository {

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
    private AttrLoader attrLoader;

    @Override
    protected AttrLoader getConfObjectLoader(){
        return attrLoader;
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
