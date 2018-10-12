/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;

/**
 * BindParameter extends BindValue with specification of access mode.
 * Default access mode for bind parameters is IN
 * 
 * @author stehlik
 */
public class BindParameter extends BindValue {
    
    private static final long serialVersionUID = 1L;
    
    private ParameterMode mode = ParameterMode.IN;

    public BindParameter(String name, Class<? extends Dt> typeClass, Dt value) {
        super(name, typeClass, value);
    }

    public BindParameter(String name, String type, Dt value) {
        super(name, type, value);
    }

    public BindParameter(String name, Dt value) {
        super(name, value);
    }

    /**
     * Plain getter got mode
     * @return the mode
     */
    public ParameterMode getMode() {
        return mode;
    }

    /**
     * Plain setter for mode
     * @param mode the mode to set
     */
    public void setMode(ParameterMode mode) {
        this.mode = mode;
    }
    
}
