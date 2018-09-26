/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

/**
 * Represents column in select query.
 * 
 * @author stehlik
 */
public interface SqlColumn {
    
    public void buildSql(CodeBuilder code);
    
    public void buildSqlNoNewLine(CodeBuilder code);

}
