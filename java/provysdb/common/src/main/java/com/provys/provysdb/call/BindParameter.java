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
    
    private final ParameterMode mode;

    public BindParameter(String name, Dt value) {
        super(name, value);
        this.mode = ParameterMode.IN;
    }

    public BindParameter(String name, Dt value, ParameterMode mode) {
        super(name, value);
        this.mode = mode;
    }

    /**
     * Plain getter got mode
     * @return the mode
     */
    public ParameterMode getMode() {
        return mode;
    }

}
