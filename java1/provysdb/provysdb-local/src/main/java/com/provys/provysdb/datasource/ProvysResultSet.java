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
     * of this {@code ResultSet} object as a {@code DtBoolean} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtBoolean} value corresponding to specified
     * {@code CHAR} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtBoolean getDtBoolean(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtBoolean} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtBoolean} value corresponding to specified
     * {@code CHAR} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtBoolean getDtBoolean(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptBoolean} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptBoolean} value corresponding to specified
     * {@code CHAR} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptBoolean getDtOptBoolean(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptBoolean} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptBoolean} value corresponding to specified
     * {@code CHAR} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptBoolean getDtOptBoolean(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtInteger} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtInteger} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtInteger getDtInteger(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtInteger} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtInteger} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtInteger getDtInteger(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptInteger} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptInteger} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptInteger getDtOptInteger(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptInteger} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptInteger} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptInteger getDtOptInteger(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtName} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtName} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtName getDtName(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtName} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtName} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtName getDtName(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptName} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptName} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptName getDtOptName(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptName} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptName} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptName getDtOptName(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtNameNm} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtNameNm} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNameNm getDtNameNm(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtNameNm} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtNameNm} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNameNm getDtNameNm(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptNameNm} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptNameNm} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptNameNm getDtOptNameNm(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptNameNm} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptNameNm} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptNameNm getDtOptNameNm(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtNumber} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtNumber} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNumber getDtNumber(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtNumber} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtNumber} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtNumber getDtNumber(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptNumber} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptNumber} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptNumber getDtOptNumber(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptNumber} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptNumber} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptNumber getDtOptNumber(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtUid} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtUid} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtUid getDtUid(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtUid} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtUid} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtUid getDtUid(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptUid} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptUid} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptUid getDtOptUid(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptUid} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptUid} value corresponding to specified
     * {@code NUMBER} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptUid getDtOptUid(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtVarchar} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtVarchar} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtVarchar getDtVarchar(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtVarchar} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtVarchar} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtVarchar getDtVarchar(String columnLabel) throws SQLException;
  
    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptVarchar} type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return {@code DtOptVarchar} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnIndex is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptVarchar getDtOptVarchar(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this {@code ResultSet} object as a {@code DtOptVarchar} type.
     *
     * @param columnLabel the label for the column specified with the SQL AS
     * clause.  If the SQL AS clause was not specified, then the label is the
     * name of the column
     * @return {@code DtOptVarchar} value corresponding to specified
     * {@code VARCHAR2} column
     * @exception SQLException if the columnLabel is not valid, if a database
     * access error occurs or this method is called on a closed result set
     */
    DtOptVarchar getDtOptVarchar(String columnLabel) throws SQLException;
  
}
