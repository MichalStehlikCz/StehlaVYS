/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.call;

import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.DtRepository;
import java.util.Optional;

/**
 * BindParameter extends BindValue with specification of access mode.
 * Default access mode for bind parameters is IN
 * 
 * @author stehlik
 */
public class BindParameter extends BindValue {
    
    private static final long serialVersionUID = 1L;
    
    private final Optional<Integer> precision;
    private final ParameterMode mode;

    public BindParameter(String name, Dt value) {
        super(name, value);
        this.precision = DtRepository.validatePrecision(
                value.getClass().getSimpleName(), Optional.empty());
        this.mode = ParameterMode.IN;
    }

    public BindParameter(String name, Dt value, ParameterMode mode) {
        super(name, value);
        this.precision = DtRepository.validatePrecision(
                value.getClass().getSimpleName(), Optional.empty());
        this.mode = mode;
    }

    public BindParameter(String name, Dt value, ParameterMode mode
            , Optional<Integer> precision) {
        super(name, value);
        this.precision = DtRepository.validatePrecision(
                value.getClass().getSimpleName(), precision);
        this.mode = mode;
    }

    /**
     * Plain getter for precision.
     * @return the precision
     */
    public Optional<Integer> getPrecision() {
        return precision;
    }

    /**
     * Plain getter for mode.
     * @return the mode
     */
    public ParameterMode getMode() {
        return mode;
    }

}
