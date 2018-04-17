/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.connection;
import java.sql.SQLException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 *
 * @author stehlik
 */
public class ProvysConnectionPoolDataSource extends OracleDataSource
        implements ConnectionPoolDataSource {

    private final OracleConnectionPoolDataSource oraclePool;

    public ProvysConnectionPoolDataSource() throws SQLException {
        oraclePool = new OracleConnectionPoolDataSource();
    }

    @Override
    public PooledConnection getPooledConnection() throws SQLException {
        return oraclePool.getPooledConnection();
    }

    @Override
    public PooledConnection getPooledConnection(String userName, String pwd)
            throws SQLException {
        return oraclePool.getPooledConnection();
    }
}
