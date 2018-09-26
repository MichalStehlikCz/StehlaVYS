/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.impl;

import com.provys.sqlbuilder.iface.FromElem;

/**
 * Implements simple column, with alias specified as referenced fromElem.
 * 
 * @author stehlik
 */
public class SqlColumnSimpleWithAlias extends SqlColumnSimple {
    
    final FromElem fromElem;
    
    public SqlColumnSimpleWithAlias(String column, FromElem fromElem) {
        super(column);
        this.fromElem = fromElem;
    }
}
