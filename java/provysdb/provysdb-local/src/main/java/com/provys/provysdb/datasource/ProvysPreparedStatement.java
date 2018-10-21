/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import com.provys.common.datatypes.DtBoolean;
import com.provys.common.datatypes.DtInteger;
import com.provys.common.datatypes.DtName;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtNumber;
import com.provys.common.datatypes.DtOptBoolean;
import com.provys.common.datatypes.DtOptInteger;
import com.provys.common.datatypes.DtOptName;
import com.provys.common.datatypes.DtOptNameNm;
import com.provys.common.datatypes.DtOptNumber;
import com.provys.common.datatypes.DtOptUid;
import com.provys.common.datatypes.DtOptVarchar;
import com.provys.common.datatypes.DtUid;
import com.provys.common.datatypes.DtVarchar;
import com.provys.provysdb.call.BindValue;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Extends PreparedStatement interface for use in Provys.
 * Interface is implemented by ProvysStatementImpl class
 *
 * @author stehlik
 */
public interface ProvysPreparedStatement extends PreparedStatement
        , ProvysStatement {

    @Override
    public ProvysResultSet executeQuery() throws SQLException;

    /**
     * Set {@code DtBoolean} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtBoolean(int parameterIndex, DtBoolean value)
            throws SQLException;
    
    /**
     * Set {@code DtBoolean} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtBoolean(String parameterName, DtBoolean value)
            throws SQLException;
    
    /**
     * Set {@code DtOptBoolean} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptBoolean(int parameterIndex, DtOptBoolean value)
            throws SQLException;
    
    /**
     * Set {@code DtOptBoolean} type parameter corresponding to bind variable
     * with given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptBoolean(String parameterName, DtOptBoolean value)
            throws SQLException;
    
    /**
     * Set {@code DtInteger} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtInteger(int parameterIndex, DtInteger value)
            throws SQLException;

    /**
     * Set {@code DtInteger} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtInteger(String parameterName, DtInteger value)
            throws SQLException;

    /**
     * Set {@code DtOptInteger} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptInteger(int parameterIndex, DtOptInteger value)
            throws SQLException;

    /**
     * Set {@code DtOptInteger} type parameter corresponding to bind variable
     * with given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptInteger(String parameterName, DtOptInteger value)
            throws SQLException;

    /**
     * Set {@code DtName} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtName(int parameterIndex, DtName value) throws SQLException;

    /**
     * Set {@code DtName} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtName(String parameterName, DtName value) throws SQLException;

    /**
     * Set {@code DtOptName} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptName(int parameterIndex, DtOptName value)
            throws SQLException;

    /**
     * Set {@code DtOptName} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptName(String parameterName, DtOptName value)
            throws SQLException;

    /**
     * Set {@code DtNameNm} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNameNm(int parameterIndex, DtNameNm value)
            throws SQLException;

    /**
     * Set {@code DtNameNm} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNameNm(String parameterName, DtNameNm value)
            throws SQLException;

    /**
     * Set {@code DtOptNameNm} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptNameNm(int parameterIndex, DtOptNameNm value)
            throws SQLException;

    /**
     * Set {@code DtOptNameNm} type parameter corresponding to bind variable
     * with given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptNameNm(String parameterName, DtOptNameNm value)
            throws SQLException;

    /**
     * Set {@code DtNumber} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNumber(int parameterIndex, DtNumber value)
            throws SQLException;

    /**
     * Set {@code DtNumber} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNumber(String parameterName, DtNumber value)
            throws SQLException;

    /**
     * Set {@code DtOptNumber} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptNumber(int parameterIndex, DtOptNumber value)
            throws SQLException;

    /**
     * Set {@code DtOptNumber} type parameter corresponding to bind variable
     * with given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptNumber(String parameterName, DtOptNumber value)
            throws SQLException;

    /**
     * Set {@code DtUid} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtUid(int parameterIndex, DtUid value) throws SQLException;

    /**
     * Set {@code DtUid} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtUid(String parameterName, DtUid value) throws SQLException;

    /**
     * Set {@code DtOptUid} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptUid(int parameterIndex, DtOptUid value)
            throws SQLException;

    /**
     * Set {@code DtOptUid} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptUid(String parameterName, DtOptUid value)
            throws SQLException;

    /**
     * Set {@code DtVarchar} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtVarchar(int parameterIndex, DtVarchar value)
            throws SQLException;
    
    /**
     * Set {@code DtVarchar} type parameter corresponding to bind variable with
     * given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtVarchar(String parameterName, DtVarchar value)
            throws SQLException;
    
    /**
     * Set {@code DtOptVarchar} type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptVarchar(int parameterIndex, DtOptVarchar value)
            throws SQLException;
    
    /**
     * Set {@code DtOptVarchar} type parameter corresponding to bind variable
     * with given name.
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtOptVarchar(String parameterName, DtOptVarchar value)
            throws SQLException;
    
    /**
     * Set value of bind variable to statement.
     * @param bind is bind name and value to be set
     * @throws SQLException is thrown when problem is encountered
     */
    public void setBind(BindValue bind) throws SQLException;

    /**
     * Set value of bind variables from list to statement.
     * @param binds is list of bind names and values to be set
     */
    public void setBinds(List<BindValue> binds);

}
