/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasourceimpl;

import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.datasource.ProvysStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Logger;
import oracle.jdbc.OracleStatement;

/**
 * Wrapper class for SQL Statement, adding support for PROVYS datatypes (Dt).
 *
 * @author stehlik
 */
public class ProvysStatementImpl implements ProvysStatement {

    private static final Logger LOG = Logger.getLogger(ProvysStatementImpl.class.getName());

    /**
     * Wrapped OracelStatement, implementing functionality, decorated by
     * ProvysStatementImpl
     */
    final protected OracleStatement statement;

    /**
     * Creates ProvysStatement built around supplied OracleStatement
     *
     * @param statement is statement this instance decorates
     * @throws java.sql.SQLException potentially raised by unwrap of supplied
     * statement to OracleStatement
     *
     */
    public ProvysStatementImpl(Statement statement) throws SQLException {
        this.statement = statement.unwrap(OracleStatement.class);
    }

    /**
     * Getter method for statement
     *
     * @return OracleStatement this instance decorates
     */
    private OracleStatement getStatement() {
        return this.statement;
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return getStatement().execute(sql);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return getStatement().execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return getStatement().execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return getStatement().execute(sql, columnNames);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return getStatement().executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return getStatement().executeUpdate(sql);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return getStatement().executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return getStatement().executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return getStatement().executeUpdate(sql, columnNames);
    }

    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        return getStatement().executeLargeUpdate(sql);
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return getStatement().executeLargeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return getStatement().executeLargeUpdate(sql, columnIndexes);
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return getStatement().executeLargeUpdate(sql, columnNames);
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return getStatement().getMoreResults();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return getStatement().getMoreResults(current);
    }

    @Override
    public int getResultSetType() throws SQLException {
        return getStatement().getResultSetType();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return getStatement().getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return getStatement().getUpdateCount();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return getStatement().getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        getStatement().clearWarnings();
    }

    @Override
    public void cancel() throws SQLException {
        getStatement().cancel();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return getStatement().isClosed();
    }

    @Override
    public void close() throws SQLException {
        getStatement().close();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return getStatement().isCloseOnCompletion();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        getStatement().closeOnCompletion();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        getStatement().addBatch(sql);
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return getStatement().executeBatch();
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        return getStatement().executeLargeBatch();
    }

    @Override
    public void clearBatch() throws SQLException {
        getStatement().clearBatch();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return getStatement().getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        getStatement().setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return getStatement().getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        getStatement().setMaxRows(max);
    }

    @Override
    public long getLargeMaxRows() throws SQLException {
        return getStatement().getLargeMaxRows();
    }

    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        getStatement().setLargeMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        getStatement().setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return getStatement().getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        getStatement().setQueryTimeout(seconds);
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        getStatement().setCursorName(name);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return getStatement().getFetchDirection();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        getStatement().setFetchDirection(direction);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return getStatement().getFetchSize();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        getStatement().setFetchSize(rows);
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return getStatement().getResultSetConcurrency();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getStatement().getConnection();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return getStatement().getGeneratedKeys();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return getStatement().getResultSetHoldability();
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return getStatement().isPoolable();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        getStatement().setPoolable(poolable);
    }

    @Override
    public long getLargeUpdateCount() throws SQLException {
        return getStatement().getLargeUpdateCount();
    }

    @Override
    public void defineColumnType(int columnIndex, int type)
            throws SQLException {
        getStatement().defineColumnType(columnIndex, type);
    }

    @Override
    public void defineColumnType(int columnIndex, int type, int maxSize)
            throws SQLException {
        getStatement().defineColumnType(columnIndex, type, maxSize);
    }

    @Override
    public void defineColumnType(int columnIndex, ColumnDef column)
            throws SQLException {
        if (column.getSize() > 0) {
            defineColumnType(columnIndex, column.getSqlType(), column.getSize());
        } else {
            defineColumnType(columnIndex, column.getSqlType());
        }
    }

    @Override
    public void defineColumnTypes(Map<Integer, ColumnDef> columns)
            throws SQLException {
        columns.forEach((columnIndex, column) -> {
            try {
                defineColumnType(columnIndex, column);
            } catch (SQLException e) {
                throw new ProvysSqlException(e);
            }
        });

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (isWrapperFor(iface)) {
            return iface.cast(this);
        }
        return getStatement().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return true;
        }
        return getStatement().isWrapperFor(iface);
    }

}
