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
import com.provys.common.datatypes.DtUid;
import com.provys.common.datatypes.DtVarchar;
import com.provys.provysdb.call.BindParameter;
import com.provys.provysdb.call.BindValue;
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
     * Set DtBoolean type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtBoolean(String parameterName, DtBoolean value) throws SQLException;
    
    /**
     * Get value of DtBoolean type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtBoolean getDtBoolean(int parameterIndex) throws SQLException;

    /**
     * Get value of DtBoolean type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtBoolean getDtBoolean(String parameterName) throws SQLException;

    /**
     * Set DtInteger type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtInteger(String parameterName, DtInteger value) throws SQLException;

    /**
     * Get value of DtInteger type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtInteger getDtInteger(int parameterIndex) throws SQLException;

    /**
     * Get value of DtInteger type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtInteger getDtInteger(String parameterName) throws SQLException;

    /**
     * Set DtName type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtName(String parameterName, DtName value) throws SQLException;

    /**
     * Get value of DtName type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtName getDtName(int parameterIndex) throws SQLException;

    /**
     * Get value of DtName type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtName getDtName(String parameterName) throws SQLException;

    /**
     * Set DtNameNm type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNameNm(String parameterName, DtNameNm value) throws SQLException;

    /**
     * Get value of DtNameNm type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNameNm getDtNameNm(int parameterIndex) throws SQLException;

    /**
     * Get value of DtNameNm type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNameNm getDtNameNm(String parameterName) throws SQLException;

    /**
     * Set DtNumber type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNumber(String parameterName, DtNumber value) throws SQLException;

    /**
     * Get value of DtNumber type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNumber getDtNumber(int parameterIndex) throws SQLException;

    /**
     * Get value of DtNumber type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtNumber getDtNumber(String parameterName) throws SQLException;

    /**
     * Set DtUid type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtUid(String parameterName, DtUid value) throws SQLException;

    /**
     * Get value of DtUid type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtUid getDtUid(int parameterIndex) throws SQLException;

    /**
     * Get value of DtNumber type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtUid getDtUid(String parameterName) throws SQLException;

    /**
     * Set DtVarchar type parameter corresponding to bind variable with given
     * name
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtVarchar(String parameterName, DtVarchar value) throws SQLException;
    
    /**
     * Get value of DtVarchar type OUT parameter on given position
     * @param parameterIndex is position of parameter (indexed from 1)
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtVarchar getDtVarchar(int parameterIndex) throws SQLException;

    /**
     * Get value of DtVarchar type OUT parameter with given bind name
     * @param parameterName is name of bind variable to be read
     * @return value assigned to bind variable during statement execution
     * @throws SQLException is thrown when problem is encountered
     */
    public DtVarchar getDtVarchar(String parameterName) throws SQLException;

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
    public Map<String, Object> getParameters(List<BindParameter> parameters);
}