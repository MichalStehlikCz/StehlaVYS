/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorclient;

import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.iface.JsonQueryExecutor;
import java.util.Collections;
import java.util.List;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Implementation class for JsonQueryExecutor against remote database service
 * 
 * @author stehlik
 */
public class JsonQueryExecutorClient implements JsonQueryExecutor {

    private final Client client;
    private SqlCall sqlCall;
    private List<JsonObject> data;
    
    JsonQueryExecutorClient(Client client) {
        this.client = client;
    }
    
    JsonQueryExecutorClient(Client client, SqlCall sqlCall) {
        this.client = client;
        this.sqlCall = sqlCall;
    }
    
    @Override
    public List<JsonObject> executeQuery() {
        data = client.target("http://localhost:8080/provysdb/query")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(getSqlCall()),
                        new GenericType<List<JsonObject>>() {});
        return getData();
    }

    @Override
    public List<JsonObject> getData() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public SqlCall getSqlCall() {
        return sqlCall;
    }

    @Override
    public void setSqlCall(SqlCall sqlCall) {
        this.sqlCall = sqlCall;
    }

}
