/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

/**
 * enum defines available join types.
 * 
 * @author stehlik
 */
public enum JoinType {

    /**
     * IN join - condition built as {column} IN (SELECT ...)
     */
    IN, 

    /**
     * EXISTS join - condition build as EXISTS(SELECT ...)
     */
    EXISTS, 

    /**
     * attempt to add expression using join; if it is for some reason not
     * possible, IN join will be used instead
     */
    JOIN
}
