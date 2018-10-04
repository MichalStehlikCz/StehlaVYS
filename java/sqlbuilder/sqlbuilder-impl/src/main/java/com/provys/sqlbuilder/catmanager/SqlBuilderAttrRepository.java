/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfObjectRepository;
import com.provys.common.datatypes.DtUid;
import java.util.List;
import javax.ejb.Local;

/**
 * Class defines contract with attribute repository for SqlBuilder.
 * 
 * @author stehlik
 * @param <T> is class implementing SqlBuilderAttr interface that will act as
 * representation of PROVYS attributes
 */
@Local
public interface SqlBuilderAttrRepository<T extends SqlBuilderAttr>
        extends ConfObjectRepository<T> {

    public List<T> loadByEntityId(DtUid entityId);
}
