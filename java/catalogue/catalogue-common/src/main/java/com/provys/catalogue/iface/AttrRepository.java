/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.catalogue.iface;

import com.provys.catalogue.model.Attr;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author stehlik
 */
@Local
public interface AttrRepository {

    Attr get(DtUid id);

    void load(DtUid id);

    public Map<DtNameNm, Attr> loadByEntityId(DtUid entityId);
}
