/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.iface;

import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SQLCall;
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
    public SQLCall getSqlCall();

    /**
     * @param sqlCall the sqlCall to set
     */
    public void setSqlCall(SQLCall sqlCall);
    
    /**
     * Retrieves list of available columns.
     * Initially, columns can be supplied by SQLCall. If they are not supplied,
     * they are parsed from supplied statement during execution and might be
     * later retrieved using this call
     * 
     * @return list of available columns
     */
    default public  Map<Integer, ColumnDef> getColumns() {
        return getSqlCall().getColumns();
    }
}
