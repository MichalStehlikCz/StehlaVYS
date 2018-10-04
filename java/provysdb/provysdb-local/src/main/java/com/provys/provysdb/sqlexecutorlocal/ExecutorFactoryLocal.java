/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import com.provys.provysdb.iface.ExecutorFactory;
import com.provys.provysdb.iface.JsonQueryExecutor;
import com.provys.provysdb.iface.MapQueryExecutor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author stehlik
 */
@ApplicationScoped
public class ExecutorFactoryLocal implements ExecutorFactory {

    @Inject
    private ProvysConnectionPoolDataSource dataSource;
    
    @Override
    public JsonQueryExecutor getJsonQueryExecutor() {
        return new JsonQueryExecutorLocal(dataSource);
    }
    
    @Override
    public JsonQueryExecutor getJsonQueryExecutor(SqlCall sqlCall) {
        return new JsonQueryExecutorLocal(dataSource, sqlCall);
    }
    
    @Override
    public MapQueryExecutor getMapQueryExecutor() {
        return new MapQueryExecutorLocal(dataSource);
    }
    
    @Override
    public MapQueryExecutor getMapQueryExecutor(SqlCall sqlCall) {
        return new MapQueryExecutorLocal(dataSource, sqlCall);
    }
    
}
