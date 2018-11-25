/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.common.datatypes.Dt;
import com.provys.common.error.ProvysException;
import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SqlCall;
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

    private List<Map<String, Dt>> data;

    public MapQueryExecutorLocal(ProvysConnectionPoolDataSource dataSource,
            SqlCall sqlCall) {
        super(dataSource, sqlCall);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>(10);
    }

    @Override
    protected void addRow(ProvysResultSet resultSet) {
        Map<String, Dt> row = new ConcurrentHashMap<>(
                getColumns().size());
        for (int index = 1; index <= getColumns().size(); index++) {
            // Array indexed from 0, SQL columns from 1
            ColumnDef column = getColumns().get(index-1);
            try {
                switch (column.getType()) {
                    case "DtBoolean":
                        row.put(column.getName(),
                                resultSet.getDtBoolean(index));
                        break;
                    case "DtInteger":
                        row.put(column.getName(),
                                resultSet.getDtInteger(index));
                        break;
                    case "DtName":
                        row.put(column.getName(),
                                resultSet.getDtName(index));
                        break;
                    case "DtNameNm":
                        row.put(column.getName(),
                                resultSet.getDtNameNm(index));
                        break;
                    case "DtNumber":
                        row.put(column.getName(),
                                resultSet.getDtNumber(index));
                        break;
                    case "DtUid":
                        row.put(column.getName(),
                                resultSet.getDtUid(index));
                        break;
                    case "DtVarchar":
                        row.put(column.getName(),
                                resultSet.getDtVarchar(index));
                        break;
                    default:
                        throw new UnsupportedColumnDatatypeException(
                                column.getType());
                }
            } catch (SQLException e) {
                throw new ProvysSqlException(e);
            }
        }
        data.add(row);
    }
    
    @Override
    public List<Map<String, Dt>> executeQuery() {
        execute();
        return getData();
    }

    @Override
    public List<Map<String, Dt>> getData() {
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
