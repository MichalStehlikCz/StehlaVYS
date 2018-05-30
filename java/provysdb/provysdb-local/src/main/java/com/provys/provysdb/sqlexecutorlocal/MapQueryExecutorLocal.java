/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.common.datatypes.*;
import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import com.provys.provysdb.iface.MapQueryExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author stehlik
 */
public class MapQueryExecutorLocal extends QueryExecutorLocal
        implements MapQueryExecutor {

    private List<Map<String, Object>> data;

    public MapQueryExecutorLocal(ProvysConnectionPoolDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>(10);
    }

    @Override
    protected void addRow(ResultSet resultSet) {
        Map<String, Object> row = new ConcurrentHashMap<>(columns.size()*2);
        columns.forEach((index, columnDef) -> 
        {
            try {
                switch (columnDef.getType().getSimpleName()) {
                    case "DtBoolean":
                        String value = resultSet.getString(index);
                        if (resultSet.wasNull()) {
                            row.put(columnDef.getName(), null);
                        } else {
                            row.put(columnDef.getName(), DtBoolean.fromStringValue(value));
                        }
                        break;
                    case "DtInteger":
                        size = -1;
                        break;
                    case "DtName":
                    case "DtNameNm":
                        size = 200;
                        break;
                    case "DtNumber":
                    case "DtRowId":
                    case "DtUid":
                        size = -1;
                        break;
                    case "DtVarchar":
                }
                if (resultSet.getString(index) != null) {
                    builder.add(columnDef.getName(), resultSet.getString(index));
                } else {
                    builder.addNull(columnDef.getName());
                }
            } catch (SQLException e) {
                throw new ProvysSqlException(e);
            }
        });
        data.add(row);
    }
    
    @Override
    public List<Map<String, Object>> executeQuery(SQLCall sqlCall) {
        execute(sqlCall);
        List<Map<String, Object>> result = data;
        data = null;
        return result;
    }

}
