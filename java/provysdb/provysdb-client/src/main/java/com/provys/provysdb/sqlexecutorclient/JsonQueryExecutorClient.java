/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorclient;

import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.iface.JsonQueryExecutor;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Implementation class for JsonQueryExecutor against remote database service
 * 
 * @author stehlik
 */
public class JsonQueryExecutorClient implements JsonQueryExecutor {

    private final Client client = ClientBuilder.newClient();
    
    @Override
    public List<JsonObject> executeQuery(SQLCall sqlCall) {
        return client.target("http://localhost:8080/provysdb/query")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(sqlCall),
                        new GenericType<List<JsonObject>>() {});
    }

}
