/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorclient;

import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.iface.MapQueryExecutor;
import java.lang.reflect.Type;
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
import javax.ws.rs.core.MediaType;

/**
 * Implementation class for JsonQueryExecutor against remote database service
 * 
 * @author stehlik
 */
public class MapQueryExecutorClient implements MapQueryExecutor {

    private final Client client;
    private SqlCall sqlCall;
    private List<Map<String, Object>> data;
    
    MapQueryExecutorClient(Client client) {
        this.client = client;
    }
    
    MapQueryExecutorClient(Client client, SqlCall sqlCall) {
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
        Type type = new ConcurrentHashMap<Integer, ColumnDef>(0) {}
            .getClass().getGenericSuperclass();
        sqlCall.setColumns((Map<Integer, ColumnDef>) jsonb.fromJson(
                jsonResult.getJsonObject("columns").toString(),
                type));
        JsonArray jsonData = jsonResult.getJsonArray("data");
        jsonData.forEach((jsonValue) -> 
            {
                Map<String, Object> row = new ConcurrentHashMap<>(
                        getSqlCall().getColumns().size()*2);
                getSqlCall().getColumns().forEach((index, columnDef) -> 
                    {
                        String value = jsonValue.asJsonObject().
                                get(columnDef.getName()).toString();
                        if (value.isEmpty()) {
                            row.put(columnDef.getName(), null);
                        } else {
                            row.put(columnDef.getName(), jsonb.fromJson(value,
                                    columnDef.getClass()));
                        }
                    });
                data.add(row);
            });
        return getData();
    }

    @Override
    public List<Map<String, Object>> getData() {
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
