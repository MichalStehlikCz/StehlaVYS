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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    final private SqlCall sqlCall;
    final private List<ColumnDef> columns;

    public QueryExecutorLocal(ProvysConnectionPoolDataSource dataSource,
            SqlCall sqlCall) {
        this.dataSource = dataSource;
        this.sqlCall = sqlCall;
        if (sqlCall.getColumns().isEmpty()) {
            columns = new ArrayList<>(1);
        } else {
            // we can directly assign columns, as they are never modified when
            // defined in SQLCall
            columns = sqlCall.getColumns();
        }
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
            statement.defineColumnTypes(getColumns());
            ProvysResultSet resultSet = statement.executeQuery();
            if (columns.isEmpty()) {
                // columns has to be taken from ResultSet
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columnCount = metadata.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    ColumnDef column = new ColumnDef(metadata.getColumnName(i)
                            , metadata.getColumnType(i)
                            , (metadata.getPrecision(i)==0) ? Optional.empty()
                                    : Optional.of(metadata.getPrecision(i)));
                    columns.add(column);
                }
            }
            while (resultSet.next()) {
                addRow(resultSet);
            }
        } catch (SQLException e) {
            throw new ProvysSqlException(e);
        }
    }

    @Override
    public SqlCall getSqlCall() {
        return sqlCall;
    }

    /**
     * @return the columns
     */
    @Override
    public List<ColumnDef> getColumns() {
        return Collections.unmodifiableList(columns);
    }

}
