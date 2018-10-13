/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.iface;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SqlCall;
import java.util.Map;

/**
 * Common methods for all query executor interfaces.
 * 
 * @author stehlik
 */
public interface QueryExecutor {
    /**
     * @return the sqlCall
     */
    public SqlCall getSqlCall();

    /**
     * @param sqlCall the sqlCall to set
     */
    public void setSqlCall(SqlCall sqlCall);
    
    /**
     * Set bind value.
     * 
     * @param name name of the bind to be set
     * @param value to be set
     */
    default public void modifyValue(String name, Dt value) {
        getSqlCall().modifyValue(name, value);
    }

    /**
     * Set bind value is bind variable with this name exists.
     * 
     * @param name name of the bind to be set
     * @param value to be set
     */
    default public void modifyValueIfExists(String name, Dt value) {
        getSqlCall().modifyValueIfExists(name, value);
    }

    /**
     * Retrieves list of available columns.
     * Initially, columns can be supplied by SqlCall. If they are not supplied,
 they are parsed from supplied statement during execution and might be
 later retrieved using this call
     * 
     * @return list of available columns
     */
    default public  Map<Integer, ColumnDef> getColumns() {
        return getSqlCall().getColumns();
    }
}
