/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorclient;

import com.provys.provysdb.iface.ExecutorFactory;
import com.provys.provysdb.iface.JsonQueryExecutor;
import com.provys.provysdb.iface.MapQueryExecutor;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Factory for creation of SQL executors, working as client against remote
 * ProvysDb service.
 * 
 * @author stehlik
 */
@ApplicationScoped
public class ExecutorFactoryClient implements ExecutorFactory {

    private final Client client = ClientBuilder.newClient();
    
    @Override
    public JsonQueryExecutor getJsonQueryExecutor() {
        return new JsonQueryExecutorClient(client);
    }
    
    @Override
    public MapQueryExecutor getMapQueryExecutor() {
        return new MapQueryExecutorClient(client);
    }
    
}
