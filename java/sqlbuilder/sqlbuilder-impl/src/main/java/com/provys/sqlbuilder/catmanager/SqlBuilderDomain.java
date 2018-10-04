/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.catmanager;

import com.provys.common.confobj.ConfNMObject;
import com.provys.common.datatypes.Dt;

/**
 * Interface describes methods needed from domain implementation by SqlBuilder.
 * 
 * @author stehlik
 */
public interface SqlBuilderDomain extends ConfNMObject {
    public Class<? extends Dt> getDtType();
}
