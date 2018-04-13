/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.common.datatypes;

/**
 *
 * @author stehlik
 * DtNumeric represents common ancestor for numeric PROVYS types and adds
 * method for value retrieval as double
 */
abstract public class DtNumeric extends Dt {
    
    /**
     * returns value of PROVYS number object, converted to double
     * @return effective value of PROVYS numeric type, converted to double
     */
    abstract public double getDouble();
}
