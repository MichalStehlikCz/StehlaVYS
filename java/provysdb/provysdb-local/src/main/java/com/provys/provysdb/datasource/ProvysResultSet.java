/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import com.provys.common.datatypes.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends ResultSet with support for Provys datatypes.
 * 
 * @author stehlik
 */
public interface ProvysResultSet extends ResultSet {
    
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtBoolean</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtBoolean</code> value corresponding to specified
     * <code>CHAR</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtBoolean getDtBoolean(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtBoolean</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtBoolean</code> value corresponding to specified
     * <code>CHAR</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtBoolean getDtBoolean(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtInteger</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtInteger</code> value corresponding to specified
     * <code>NUMBER</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtInteger getDtInteger(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtInteger</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtInteger</code> value corresponding to specified
     * <code>NUMBER</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtInteger getDtInteger(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtName</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtName</code> value corresponding to specified
     * <code>VARCHAR2</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtName getDtName(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtName</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtName</code> value corresponding to specified
     * <code>VARCHAR2</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtName getDtName(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtNameNm</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtNameNm</code> value corresponding to specified
     * <code>VARCHAR2</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNameNm getDtNameNm(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtNameNm</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtNameNm</code> value corresponding to specified
     * <code>VARCHAR2</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNameNm getDtNameNm(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtNumber</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtNumber</code> value corresponding to specified
     * <code>NUMBER</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNumber getDtNumber(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtNumber</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtNumber</code> value corresponding to specified
     * <code>NUMBER</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNumber getDtNumber(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtRowId</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtRowId</code> value corresponding to specified
     * <code>ROWID</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtRowId getDtRowId(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtRowId</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtRowId</code> value corresponding to specified
     * <code>ROWID</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtRowId getDtRowId(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtUid</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtUid</code> value corresponding to specified
     * <code>NUMBER</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtUid getDtUid(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtUid</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtUid</code> value corresponding to specified
     * <code>NUMBER</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtUid getDtUid(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtVarchar</code> type
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return <code>DtVarchar</code> value corresponding to specified
     * <code>VARCHAR2</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtVarchar getDtVarchar(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>DtVarchar</code>
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return <code>DtVarchar</code> value corresponding to specified
     * <code>VARCHAR2</code> column; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtVarchar getDtVarchar(String columnLabel) throws SQLException;
  
}
