/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

import java.io.Serializable;

/**
 *
 * @author micha
 */
public abstract class Dt implements Serializable{
    abstract public String getValue();
    
    @Override
    abstract public boolean equals(Object second);

    @Override
    abstract public int hashCode();
        
}
