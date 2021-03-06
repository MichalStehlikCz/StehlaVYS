/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.iface;

import com.provys.catalogue.model.Entity;
import com.provys.common.confobj.ConfNMObjectRepository;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import javax.ejb.Local;

/**
 *
 * @author stehlik
 */
@Local
public interface EntityRepository extends ConfNMObjectRepository<Entity> {

    public void loadAttrs(DtUid entityId);

    default public void loadAttrsByNm(DtNameNm nameNm) {
        loadAttrs(getByNm(nameNm).getId());
    }

}
