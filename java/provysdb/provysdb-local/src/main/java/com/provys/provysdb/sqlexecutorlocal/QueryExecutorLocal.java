/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.datasource.ProvysCallableStatement;
import com.provys.provysdb.datasource.ProvysConnection;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Parent of various executor classes.
 * Implements logic of parsing SQL statement, binding variables, defining
 * columns and iterating through ResultSet. Registers addRow as method, called
 * on each row of ResultSet. This enables to store results as either individual
 * values or JSON or XML documents
 *
 * @author stehlik
 */
abstract public class QueryExecutorLocal {

    private final ProvysConnectionPoolDataSource dataSource;

    public QueryExecutorLocal(ProvysConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * columns field is list of column definitions that will be used to declare
     * expected columns of resulting set
     */
    protected Map<Integer, ColumnDef> columns;
    
    /**
     * Initializes result set.
     * called before query execution.
     */
    abstract protected void initData();
    
    /**
     * Adds row to result data.
     * 
     * @param resultSet is result set navigated to the row to be added
    */
    abstract protected void addRow(ResultSet resultSet);
    
    /**
     * Binds all values, executes statement and returns resulting ResultSet.
     * This method should only be used with simple SELECT statement. For update
     * statements, executeUpdate should be used instead
     *
     * @param sqlCall is SQL statement with binds and column definitions
     * describing query to be executed
     */
    protected void execute(SQLCall sqlCall) {

        try (ProvysConnection connection = dataSource.getConnection()) {
            initData();
            ProvysCallableStatement statement = connection.prepareCall(sqlCall.getSql());
            statement.setBinds(sqlCall.getValues());
            statement.defineColumnTypes(sqlCall.getColumns());
            ResultSet resultSet = statement.executeQuery();
            if (sqlCall.getColumns().isEmpty()) {
                // columns has to be taken from ResultSet
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columnCount = metadata.getColumnCount();
                this.columns = new HashMap<>(columnCount);
                for (int i = 1; i<= columnCount; i++) {
                    ColumnDef column = new ColumnDef();
                    column.setName(metadata.getColumnName(i));
                    column.setType(metadata.getColumnType(i));
                    column.setSize(metadata.getPrecision(i));
                    this.columns.put(i, column);
                }
            } else {
                // columns can be taken from SQLCall
                columns = sqlCall.getColumns();
            }
            while (resultSet.next()) {
                addRow(resultSet);
            }
        } catch (SQLException e) {
            throw new ProvysSqlException(e);
        }
    }
    
}
