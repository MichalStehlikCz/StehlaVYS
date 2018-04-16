/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.sql.*;

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

    public PooledConnection getPooledConnection() throws SQLException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aconst_null
         * 1: astore_1
         * 2: aconst_null
         * 3: astore_2
         * 4: aload_0
         * 5: dup
         * 6: astore_3
         * 7: monitorenter
         * 8: aload_0
         * 9: getfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.user:Ljava/lang/String;
         * 12: astore_1
         * 13: aload_0
         * 14: getfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.password:Loracle/jdbc/internal/OpaqueString;
         * 17: invokevirtual oracle/jdbc/internal/OpaqueString.get:()Ljava/lang/String;
         * 20: astore_2
         * 21: aload_3
         * 22: monitorexit
         * 23: goto          33
         * 26: astore        4
         * 28: aload_3
         * 29: monitorexit
         * 30: aload         4
         * 32: athrow
         * 33: aload_0
         * 34: aload_1
         * 35: aload_2
         * 36: invokevirtual oracle/jdbc/pool/OracleConnectionPoolDataSource.getPooledConnection:(Ljava/lang/String;Ljava/lang/String;)Ljavax/sql/PooledConnection;
         * 39: areturn
         * Exception table:
         * from    to  target type
         * 8    23    26   any
         * 26    30    26   any
         *  */
        // </editor-fold>
    }

    public PooledConnection getPooledConnection(String string, String string1) throws SQLException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_1
         * 2: aload_2
         * 3: invokevirtual oracle/jdbc/pool/OracleConnectionPoolDataSource.getPhysicalConnection:(Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
         * 6: astore_3
         * 7: new           oracle/jdbc/pool/OraclePooledConnection
         * 10: dup
         * 11: aload_3
         * 12: invokespecial oracle/jdbc/pool/OraclePooledConnection."<init>":(Ljava/sql/Connection;)V
         * 15: astore        4
         * 17: aload_2
         * 18: ifnonnull     29
         * 21: aload_0
         * 22: getfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.password:Loracle/jdbc/internal/OpaqueString;
         * 25: invokevirtual oracle/jdbc/internal/OpaqueString.get:()Ljava/lang/String;
         * 28: astore_2
         * 29: aload         4
         * 31: aload_1
         * 32: ldc           \"
         * 34: invokevirtual java/lang/String.startsWith:(Ljava/lang/String;)Z
         * 37: ifne          47
         * 40: aload_1
         * 41: invokevirtual java/lang/String.toLowerCase:()Ljava/lang/String;
         * 44: goto          48
         * 47: aload_1
         * 48: aload_2
         * 49: invokevirtual oracle/jdbc/pool/OraclePooledConnection.setUserName:(Ljava/lang/String;Ljava/lang/String;)V
         * 52: aload         4
         * 54: areturn
         *  */
        // </editor-fold>
    }

    PooledConnection getPooledConnection(Properties prprts) throws SQLException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_1
         * 2: invokevirtual oracle/jdbc/pool/OracleConnectionPoolDataSource.getPhysicalConnection:(Ljava/util/Properties;)Ljava/sql/Connection;
         * 5: astore_2
         * 6: new           oracle/jdbc/pool/OraclePooledConnection
         * 9: dup
         * 10: aload_2
         * 11: invokespecial oracle/jdbc/pool/OraclePooledConnection."<init>":(Ljava/sql/Connection;)V
         * 14: astore_3
         * 15: aload_1
         * 16: ldc           user
         * 18: invokevirtual java/util/Properties.getProperty:(Ljava/lang/String;)Ljava/lang/String;
         * 21: astore        4
         * 23: aload         4
         * 25: ifnonnull     39
         * 28: aload_2
         * 29: checkcast     oracle/jdbc/internal/OracleConnection
         * 32: invokeinterface oracle/jdbc/internal/OracleConnection.getUserName:()Ljava/lang/String;
         * 37: astore        4
         * 39: aload_1
         * 40: ldc           password
         * 42: invokevirtual java/util/Properties.getProperty:(Ljava/lang/String;)Ljava/lang/String;
         * 45: astore        5
         * 47: aload         5
         * 49: ifnonnull     61
         * 52: aload_0
         * 53: getfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.password:Loracle/jdbc/internal/OpaqueString;
         * 56: invokevirtual oracle/jdbc/internal/OpaqueString.get:()Ljava/lang/String;
         * 59: astore        5
         * 61: aload_3
         * 62: aload         4
         * 64: ldc           \"
         * 66: invokevirtual java/lang/String.startsWith:(Ljava/lang/String;)Z
         * 69: ifne          80
         * 72: aload         4
         * 74: invokevirtual java/lang/String.toLowerCase:()Ljava/lang/String;
         * 77: goto          82
         * 80: aload         4
         * 82: aload         5
         * 84: invokevirtual oracle/jdbc/pool/OraclePooledConnection.setUserName:(Ljava/lang/String;Ljava/lang/String;)V
         * 87: aload_3
         * 88: areturn
         *  */
        // </editor-fold>
    }

    protected Connection getPhysicalConnection() throws SQLException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.user:Ljava/lang/String;
         * 5: aload_0
         * 6: getfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.password:Loracle/jdbc/internal/OpaqueString;
         * 9: invokevirtual oracle/jdbc/internal/OpaqueString.get:()Ljava/lang/String;
         * 12: invokespecial oracle/jdbc/pool/OracleDataSource.getConnection:(Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
         * 15: areturn
         *  */
        // </editor-fold>
    }

    protected Connection getPhysicalConnection(String string, String string1, String string2) throws SQLException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_1
         * 2: putfield      oracle/jdbc/pool/OracleConnectionPoolDataSource.url:Ljava/lang/String;
         * 5: aload_0
         * 6: aload_2
         * 7: aload_3
         * 8: invokespecial oracle/jdbc/pool/OracleDataSource.getConnection:(Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
         * 11: areturn
         *  */
        // </editor-fold>
    }

    protected Connection getPhysicalConnection(String string, String string1) throws SQLException {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_1
         * 2: aload_2
         * 3: invokespecial oracle/jdbc/pool/OracleDataSource.getConnection:(Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
         * 6: areturn
         *  */
        // </editor-fold>
    }
}
