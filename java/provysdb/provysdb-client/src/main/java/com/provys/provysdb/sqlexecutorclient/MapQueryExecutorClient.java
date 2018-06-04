/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorclient;

import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.iface.MapQueryExecutor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Implementation class for JsonQueryExecutor against remote database service
 * 
 * @author stehlik
 */
public class MapQueryExecutorClient implements MapQueryExecutor {

    private final Client client;
    private SQLCall sqlCall;
    private List<Map<String, Object>> data;
    
    MapQueryExecutorClient(Client client) {
        this.client = client;
    }
    
    MapQueryExecutorClient(Client client, SQLCall sqlCall) {
        this.client = client;
        this.sqlCall = sqlCall;
    }
    
    @Override
    public List<Map<String, Object>> executeQuery() {
        JsonObject jsonResult = client.target(
                "http://localhost:8080/provysdb/parseAndQuery")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(sqlCall), JsonObject.class);
        Jsonb jsonb = JsonbBuilder.create();
        if (jsonResult.containsKey("columns")) {
            Type type = new ConcurrentHashMap<Integer, ColumnDef>(0) {}
                .getClass().getGenericSuperclass();
            sqlCall.setColumns(jsonb.fromJson(
                    jsonResult.getJsonObject("columns").toString(),
                    type));
        }
        JsonArray jsonData = jsonResult.getJsonArray("data");
        jsonData.forEach(     );
        return getData();
    }

    @Override
    public List<Map<String, Object>> getData() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public SQLCall getSqlCall() {
        return sqlCall;
    }

    @Override
    public void setSqlCall(SQLCall sqlCall) {
        this.sqlCall = sqlCall;
    }

}
