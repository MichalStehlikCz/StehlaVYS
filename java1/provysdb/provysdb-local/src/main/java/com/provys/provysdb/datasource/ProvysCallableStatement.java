/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import com.provys.common.datatypes.Dt;
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
import com.provys.provysdb.call.BindParameter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Interface extends CallableStatement for use in PROVYS system. It is
 * implemented by ProvysCallableStatementImpl class in datasource module
 *
 * @author stehlik
 */
public interface ProvysCallableStatement extends ProvysPreparedStatement,
        CallableStatement {

    /**
     * Get value of {@code DtBoolean} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtBoolean getDtBoolean(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtBoolean} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtBoolean getDtBoolean(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptBoolean} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptBoolean getDtOptBoolean(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptBoolean} type OUT parameter with given bind
     * name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptBoolean getDtOptBoolean(String parameterName)
            throws SQLException;

    /**
     * Get value of {@code DtInteger} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtInteger getDtInteger(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtInteger} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtInteger getDtInteger(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptInteger} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptInteger getDtOptInteger(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptInteger} type OUT parameter with given bind
     * name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptInteger getDtOptInteger(String parameterName)
            throws SQLException;

    /**
     * Get value of {@code DtName} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtName getDtName(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtName} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtName getDtName(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptName} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptName getDtOptName(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptName} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptName getDtOptName(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtNameNm} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNameNm getDtNameNm(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtNameNm} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNameNm getDtNameNm(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptNameNm} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptNameNm getDtOptNameNm(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptNameNm} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptNameNm getDtOptNameNm(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtNumber} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNumber getDtNumber(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtNumber} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNumber getDtNumber(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptNumber} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptNumber getDtOptNumber(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptNumber} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptNumber getDtOptNumber(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtUid} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtUid getDtUid(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtUid} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtUid getDtUid(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptUid} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptUid getDtOptUid(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptUid} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptUid getDtOptUid(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtVarchar} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtVarchar getDtVarchar(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtVarchar} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtVarchar getDtVarchar(String parameterName) throws SQLException;

    /**
     * Get value of {@code DtOptVarchar} type OUT parameter on given position.
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptVarchar getDtOptVarchar(int parameterIndex) throws SQLException;

    /**
     * Get value of {@code DtOptVarchar} type OUT parameter with given bind name.
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtOptVarchar getDtOptVarchar(String parameterName)
            throws SQLException;

    /**
     * Set value of parameter to statement and define its output type if
     * appropriate.
     * @param parameter is bind parameter to be set / registered
     * @throws SQLException is thrown when problem is encountered
     */
    public void setParameter(BindParameter parameter) throws SQLException;

    /**
     * Set value of parameters from list to statement and define their output
     * types if appropriate.
     * @param parameters is list of parameters to be set / registered
     */
    public void setParameters(List<BindParameter> parameters);

    /**
     * Get value of supplied parameter after execution of stored procedure.
     * 
     * @param parameter whose value is to be retrieved
     * @return value of given IN/OUT or OUT parameter as set by statement
     * execution
     * @throws SQLException when problem is encountered
     */
    public Object getParameter(BindParameter parameter) throws SQLException;

    /**
     * Get values of IN/OUT and OUT parameters in supplied list.
     * IN parameters in list are ignored and their value is not included in
     * output collection.
     * 
     * @param parameters is list of parameters which should be retrieved
     * @return collection of parameter names and their respective values
     */
    public Map<String, Dt> getParameters(List<BindParameter> parameters);
}
