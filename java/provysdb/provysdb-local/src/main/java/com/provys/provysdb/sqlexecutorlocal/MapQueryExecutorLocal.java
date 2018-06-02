/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.common.error.ProvysException;
import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.datasource.ProvysResultSet;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import com.provys.provysdb.iface.MapQueryExecutor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    public MapQueryExecutorLocal(ProvysConnectionPoolDataSource dataSource,
            SQLCall sqlCall) {
        super(dataSource, sqlCall);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>(10);
    }

    @Override
    protected void addRow(ProvysResultSet resultSet) {
        Map<String, Object> row = new ConcurrentHashMap<>(
                getSqlCall().getColumns().size()*2);
        getSqlCall().getColumns().forEach((index, columnDef) -> 
        {
            try {
                switch (columnDef.getType()) {
                    case "DtBoolean":
                        row.put(columnDef.getName(),
                                resultSet.getDtBoolean(index));
                        break;
                    case "DtInteger":
                        row.put(columnDef.getName(),
                                resultSet.getDtInteger(index));
                        break;
                    case "DtName":
                        row.put(columnDef.getName(),
                                resultSet.getDtName(index));
                        break;
                    case "DtNameNm":
                        row.put(columnDef.getName(),
                                resultSet.getDtNameNm(index));
                        break;
                    case "DtNumber":
                        row.put(columnDef.getName(),
                                resultSet.getDtNumber(index));
                        break;
                    case "DtRowId":
                        row.put(columnDef.getName(),
                                resultSet.getDtRowId(index));
                        break;
                    case "DtUid":
                        row.put(columnDef.getName(),
                                resultSet.getDtUid(index));
                        break;
                    case "DtVarchar":
                        row.put(columnDef.getName(),
                                resultSet.getDtVarchar(index));
                        break;
                    default:
                        throw new UnsupportedColumnDatatypeException(
                                columnDef.getType());
                }
            } catch (SQLException e) {
                throw new ProvysSqlException(e);
            }
        });
        data.add(row);
    }
    
    @Override
    public List<Map<String, Object>> executeQuery() {
        execute();
        return getData();
    }

    @Override
    public List<Map<String, Object>> getData() {
        return Collections.unmodifiableList(data);
    }
    
    /**
     * Exception raised when value supplied to ColumnDef is not one of supported
     * types
     */
    @SuppressWarnings("PublicInnerClass")
    static public class UnsupportedColumnDatatypeException
            extends ProvysException {

        private static final long serialVersionUID = 1L;

        UnsupportedColumnDatatypeException(String type) {
            super("Unsupported class for column definition: " + type);
        }
    }

}
