/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import com.provys.provysdb.call.ColumnDef;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Interface that wraps Statement for PROVYS use
 * 
 * @author stehlik
 */
public interface ProvysStatement extends Statement {
    
    @Override
    public ProvysResultSet executeQuery(String sql) throws SQLException;

    @Override
    public ProvysResultSet getResultSet() throws SQLException;

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
     * @param maxPrecision is maximal size (in characters / digits) of defined
     * column
     * @throws SQLException is exception thrown during assignment
     */
    public void defineColumnType(int columnIndex, int type, int maxPrecision)
            throws SQLException;

    /**
     * Defines the type you will use to retrieve data and maximal size of data
     * from a particular database table column.
     * @param columnIndex is column order (starting from 1)
     * @param type is assigned type (java.sql.Types)
     * @param maxPrecision is maximal size (in characters / digits) of defined
     * column
     * @param scale is scale of column - number of digits to the right of
     * decimal point
     * @throws SQLException is exception thrown during assignment
     */
    public void defineColumnType(int columnIndex, int type, int maxPrecision
            , short scale) throws SQLException;

    /**
     * Defines the type of column based on ColumnDef.
     * @param columnIndex is column order (starting from 1)
     * @param column is column definition (type and potentially size)
     * @throws SQLException is exception thrown during assignment
     */
    public void defineColumnType(int columnIndex, ColumnDef column)
            throws SQLException;

    /**
     * Defines types of columns based on list of ColumnDefs.
     * @param columns is list of column definitions
     * @throws SQLException is exception thrown during assignment
     */
    public void defineColumnTypes(List<ColumnDef> columns)
            throws SQLException;
}
