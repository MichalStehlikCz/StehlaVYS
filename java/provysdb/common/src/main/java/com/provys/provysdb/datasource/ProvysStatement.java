/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Interface that wraps Statement for PROVYS use
 * 
 * @author stehlik
 */
public interface ProvysStatement extends Statement {
    
    /**
     * Defines the type you will use to retrieve data from a particular database
     * table column.
     * @param columnIndex is column order (starting from 1)
     * @param type is assigned type (java.sql.Types)
     * @throws SQLException is exception thrown during assignment
     */
    public void defineColumnType(int columnIndex, int type) throws SQLException;
    
    /**
     * Defines the type you will use to retrieve data and maximal size of data
     * from a particular database table column.
     * @param columnIndex is column order (starting from 1)
     * @param type is assigned type (java.sql.Types)
     * @param maxSize is maximal size (in database units) of defined column
     * @throws SQLException is exception thrown during assignment
     */
    public void defineColumnType(int columnIndex, int type, int maxSize)
            throws SQLException;

}
