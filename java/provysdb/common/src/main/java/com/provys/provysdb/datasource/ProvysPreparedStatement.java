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
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Extends PreparedStatement interface for use in Provys.
 * Interface is implemented by ProvysStatementImpl class
 *
 * @author stehlik
 */
public interface ProvysPreparedStatement extends PreparedStatement {

    /**
     * Set DtBoolean type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtBoolean(int parameterIndex, DtBoolean value) throws SQLException;
    
    /**
     * Set DtInteger type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtInteger(int parameterIndex, DtInteger value) throws SQLException;

    /**
     * Set DtName type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtName(int parameterIndex, DtName value) throws SQLException;

    /**
     * Set DtNameNm type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNameNm(int parameterIndex, DtNameNm value) throws SQLException;

    /**
     * Set DtNumber type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNumber(int parameterIndex, DtNumber value) throws SQLException;

    /**
     * Set DtUid type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtUid(int parameterIndex, DtUid value) throws SQLException;

    /**
     * Set DtVarchar type parameter on given position
     * @param parameterIndex is index of parameter to be set (starting from 1)
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtVarchar(int parameterIndex, DtVarchar value) throws SQLException;
    
}
