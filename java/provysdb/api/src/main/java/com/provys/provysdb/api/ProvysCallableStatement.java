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
     * Set DtBoolean type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtBoolean(String parameterName, DtBoolean value) throws SQLException;
    
    /**
     * Set DtInteger type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtInteger(String parameterName, DtInteger value) throws SQLException;

    /**
     * Set DtName type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtName(String parameterName, DtName value) throws SQLException;

    /**
     * Set DtNameNm type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNameNm(String parameterName, DtNameNm value) throws SQLException;

    /**
     * Set DtNumber type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtNumber(String parameterName, DtNumber value) throws SQLException;

    /**
     * Set DtUid type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtUid(String parameterName, DtUid value) throws SQLException;

    /**
     * Set DtVarchar type parameter on given position
     * @param parameterName is name of bind variable to be associated with given
     * parameter
     * @param value is value to be set to given parameter
     * @throws SQLException is thrown when problem is encountered
     */
    public void setDtVarchar(String parameterName, DtVarchar value) throws SQLException;
    
}
