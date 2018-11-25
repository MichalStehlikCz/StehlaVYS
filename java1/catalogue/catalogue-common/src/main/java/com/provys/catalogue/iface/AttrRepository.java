/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.iface;

import com.provys.catalogue.model.Attr;
import com.provys.common.confobj.ConfObjectRepository;
import com.provys.common.datatypes.DtUid;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author stehlik
 */
@Local
public interface AttrRepository extends ConfObjectRepository<Attr> {

    public List<Attr> loadByEntityId(DtUid entityId);
}
