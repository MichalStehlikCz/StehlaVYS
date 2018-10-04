/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.sqlexecutorlocal;

import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.datasource.ProvysCallableStatement;
import com.provys.provysdb.datasource.ProvysConnection;
import com.provys.provysdb.datasource.ProvysResultSet;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import com.provys.provysdb.iface.QueryExecutor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Parent of various executor classes.
 * Implements logic of parsing SQL statement, binding variables, defining
 * columns and iterating through ResultSet. Registers addRow as method, called
 * on each row of ResultSet. This enables to store results as either individual
 * values or JSON or XML documents
 *
 * @author stehlik
 */
abstract public class QueryExecutorLocal implements QueryExecutor {

    private final ProvysConnectionPoolDataSource dataSource;

    /**
     * defines statement(s) to be executed
     */
    private SqlCall sqlCall;

    public QueryExecutorLocal(ProvysConnectionPoolDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public QueryExecutorLocal(ProvysConnectionPoolDataSource dataSource,
            SqlCall sqlCall) {
        this.dataSource = dataSource;
        this.sqlCall = sqlCall;
    }
    
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
    abstract protected void addRow(ProvysResultSet resultSet);
    
    /**
     * Binds all values, executes statement and returns resulting ResultSet.
     * This method should only be used with simple SELECT statement. For update
     * statements, executeUpdate should be used instead
     */
    protected void execute() {

        try (ProvysConnection connection = dataSource.getConnection()) {
            initData();
            ProvysCallableStatement statement = connection.prepareCall(getSqlCall().getSql());
            statement.setBinds(getSqlCall().getValues());
            statement.defineColumnTypes(getSqlCall().getColumns());
            ProvysResultSet resultSet = statement.executeQuery();
            if (getSqlCall().getColumns().isEmpty()) {
                // columns has to be taken from ResultSet
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columnCount = metadata.getColumnCount();
                for (int i = 1; i<= columnCount; i++) {
                    ColumnDef column = new ColumnDef();
                    column.setName(metadata.getColumnName(i));
                    column.setType(metadata.getColumnType(i));
                    column.setSize(metadata.getPrecision(i));
                    getSqlCall().addColumn(i, column);
                }
            }
            while (resultSet.next()) {
                addRow(resultSet);
            }
        } catch (SQLException e) {
            throw new ProvysSqlException(e);
        }
    }

    /**
     * @return the sqlCall
     */
    @Override
    public SqlCall getSqlCall() {
        return sqlCall;
    }

    /**
     * @param sqlCall the sqlCall to set
     */
    @Override
    public void setSqlCall(SqlCall sqlCall) {
        this.sqlCall = sqlCall;
    }
    
}
