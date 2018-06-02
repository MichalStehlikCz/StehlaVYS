/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorclient;

import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.iface.MapQueryExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Implementation class for JsonQueryExecutor against remote database service
 * 
 * @author stehlik
 */
public class MapQueryExecutorClient implements MapQueryExecutor {

    private final Client client;
    
    MapQueryExecutorClient(Client client) {
        this.client = client;
    }
    
    @Override
    public List<Map<String, Object>> executeQuery(SQLCall sqlCall) {
        JsonObject jsonResult = client.target("http://localhost:8080/provysdb/query")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(sqlCall), JsonObject.class);
        List<Map<String, Object>> result = new ArrayList<>(10);
        
        return result;
    }

}
