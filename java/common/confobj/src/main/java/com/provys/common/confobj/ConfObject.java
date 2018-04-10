/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.confobj;

import com.provys.common.datatypes.DtUid;
import com.provys.common.annotation.*;

/**
 *
 * @author stehlik
 */
abstract public class ConfObject {
    
    @ProvysKey
    protected final DtUid id;

    public ConfObject(DtUid id) {
        this.id = id;
    }

    public DtUid getId() {
        return this.id;
    }
    
}
