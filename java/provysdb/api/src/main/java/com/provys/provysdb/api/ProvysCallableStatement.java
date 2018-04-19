/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import com.provys.common.datatypes.DtBoolean;
import com.provys.common.datatypes.DtInteger;
import com.provys.common.datatypes.DtName;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtNumber;
import com.provys.common.datatypes.DtUid;
import com.provys.common.datatypes.DtVarchar;
import java.sql.CallableStatement;
import java.sql.SQLException;

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

    public DtVarchar getDtVarchar(String parameterName) throws SQLException;

}
