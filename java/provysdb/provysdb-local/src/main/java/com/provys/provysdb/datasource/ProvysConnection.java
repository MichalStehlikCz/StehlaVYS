/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import com.provys.common.datatypes.Dt;
import com.provys.provysdb.call.ProcCall;
import com.provys.provysdb.call.SqlCall;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 *
 * @author stehlik
 */
public interface ProvysConnection extends Connection {

    @Override
    void abort(Executor executor) throws SQLException;

    @Override
    void clearWarnings() throws SQLException;

    @Override
    void close() throws SQLException;

    @Override
    void commit() throws SQLException;

    @Override
    Array createArrayOf(String typeName, Object[] elements) throws SQLException;

    @Override
    Blob createBlob() throws SQLException;

    @Override
    Clob createClob() throws SQLException;

    @Override
    NClob createNClob() throws SQLException;

    @Override
    SQLXML createSQLXML() throws SQLException;

    @Override
    ProvysStatement createStatement() throws SQLException;

    @Override
    ProvysStatement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException;

    @Override
    ProvysStatement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException;

    @Override
    Struct createStruct(String typeName, Object[] attributes) throws SQLException;

    @Override
    boolean getAutoCommit() throws SQLException;

    @Override
    String getCatalog() throws SQLException;

    @Override
    String getClientInfo(String name) throws SQLException;

    @Override
    Properties getClientInfo() throws SQLException;

    @Override
    int getHoldability() throws SQLException;

    @Override
    DatabaseMetaData getMetaData() throws SQLException;

    @Override
    int getNetworkTimeout() throws SQLException;

    @Override
    String getSchema() throws SQLException;

    @Override
    int getTransactionIsolation() throws SQLException;

    @Override
    Map<String, Class<?>> getTypeMap() throws SQLException;

    @Override
    SQLWarning getWarnings() throws SQLException;

    @Override
    boolean isClosed() throws SQLException;

    @Override
    boolean isReadOnly() throws SQLException;

    @Override
    boolean isValid(int timeout) throws SQLException;

    @Override
    String nativeSQL(String sql) throws SQLException;

    @Override
    ProvysCallableStatement prepareCall(String sql) throws SQLException;

    @Override
    ProvysCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException;

    @Override
    ProvysCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException;

    @Override
    ProvysPreparedStatement prepareStatement(String sql) throws SQLException;

    @Override
    ProvysPreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException;

    @Override
    ProvysPreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException;

    @Override
    ProvysPreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException;

    @Override
    ProvysPreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException;

    @Override
    ProvysPreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException;

    @Override
    void releaseSavepoint(Savepoint savepoint) throws SQLException;

    @Override
    void rollback() throws SQLException;

    @Override
    void rollback(Savepoint savepoint) throws SQLException;

    @Override
    void setAutoCommit(boolean autoCommit) throws SQLException;

    @Override
    void setCatalog(String catalog) throws SQLException;

    @Override
    void setClientInfo(String name, String value) throws SQLClientInfoException;

    @Override
    void setClientInfo(Properties properties) throws SQLClientInfoException;

    @Override
    void setHoldability(int holdability) throws SQLException;

    @Override
    void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException;

    @Override
    void setReadOnly(boolean readOnly) throws SQLException;

    @Override
    Savepoint setSavepoint() throws SQLException;

    @Override
    Savepoint setSavepoint(String name) throws SQLException;

    @Override
    void setSchema(String schema) throws SQLException;

    @Override
    void setTransactionIsolation(int level) throws SQLException;

    @Override
    void setTypeMap(Map<String, Class<?>> map) throws SQLException;

    /**
     * Binds all values, executes statement and returns number of affected rows.
     * This method should only be used with simple UPDATE / DELETE statements.
     * For select statements, executeQuery should be used instead
     * 
     * @param sqlCall is SQL statement with binds and column definitions
     * describing query to be executed
     * @return number of modified rows
     * @throws java.sql.SQLException when any exception occurs during SQL
     * processing
     */
    public int executeUpdate(SqlCall sqlCall) throws SQLException;

    /**
     * Binds all variables, executes statement and returns values of OUT
     * parameters.
     * This method is meant to execute anonymous PL/SQL blocks. If there are no
     * OUT parameters, method returns null instead of empty collection.
     * 
     * @param procCall is procedural statement with parameter definitions
     * @return collection of OUT parameters with values
     * @throws java.sql.SQLException when any exception occurs during SQL
     * processing
     */
    public Map<String, Dt> executeProc(ProcCall procCall)
            throws SQLException;

    @Override
    <T> T unwrap(Class<T> iface) throws SQLException;

    @Override
    boolean isWrapperFor(Class<?> iface) throws SQLException;
}
