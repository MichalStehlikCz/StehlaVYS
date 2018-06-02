/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.datasource.ProvysResultSet;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import com.provys.provysdb.iface.JsonQueryExecutor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Executor that returns query result as list of Json objects.
 * 
 * @author stehlik
 */
public class JsonQueryExecutorLocal extends QueryExecutorLocal
        implements JsonQueryExecutor {
    
    private List<JsonObject> data;

    public JsonQueryExecutorLocal(ProvysConnectionPoolDataSource dataSource) {
        super(dataSource);
    }

    public JsonQueryExecutorLocal(ProvysConnectionPoolDataSource dataSource,
            SQLCall sqlCall) {
        super(dataSource, sqlCall);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>(10);
    }

    @Override
    protected void addRow(ProvysResultSet resultSet) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        getSqlCall().getColumns().forEach((index, columnDef) -> 
        {
            try {
                if (resultSet.getString(index) != null) {
                    builder.add(columnDef.getName(), resultSet.getString(index));
                } else {
                    builder.addNull(columnDef.getName());
                }
            } catch (SQLException e) {
                throw new ProvysSqlException(e);
            }
        });
        data.add(builder.build());
    }
    
    @Override
    public List<JsonObject> executeQuery() {
        execute();
        return getData();
    }

    @Override
    public List<JsonObject> getData() {
        return Collections.unmodifiableList(data);
    }
}
