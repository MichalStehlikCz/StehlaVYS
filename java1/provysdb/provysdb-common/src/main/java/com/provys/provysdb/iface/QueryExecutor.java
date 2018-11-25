/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.iface;

import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SqlCall;
import java.util.List;

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
     * Retrieves list of available columns.
     * Initially, columns can be supplied by SqlCall. If they are not supplied,
     * they are parsed from supplied statement during execution and might be
     * later retrieved using this call
     * 
     * @return list of available columns
     */
    public List<ColumnDef> getColumns();

}
