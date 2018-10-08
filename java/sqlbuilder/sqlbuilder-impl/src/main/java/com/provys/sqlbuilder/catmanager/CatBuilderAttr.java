/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfObject;
import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.DtNameNm;

/**
 * Interface defines methods needed by SqlBuilder from attribute implementation.
 * 
 * @author stehlik
 */
public interface CatBuilderAttr extends ConfObject {
    public DtNameNm getNm();
    public String getSql(boolean useView);
    public boolean needsTable();
    public CatBuilderDomain getDomain();
    default public Class<? extends Dt> getTypeClass() {
        return getDomain().getTypeClass();
    }
    public boolean isIndexed();
}
