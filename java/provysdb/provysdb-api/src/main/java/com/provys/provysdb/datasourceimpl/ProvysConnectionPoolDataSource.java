/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasourceimpl;

import com.provys.provysdb.datasource.ProvysConnectionPoolDataSourceLocal;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.CommonDataSource;
import javax.sql.DataSource;
import oracle.ucp.ConnectionLabelingCallback;
import oracle.ucp.jdbc.LabelableConnection;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

/**
 * Datasource to be used for accesing PROVYS database. Based on Oracle
 * connection pool, this datasource supports switching of session context to one
 * representing Java session
 *
 * @author stehlik
 */
@Singleton
@ApplicationScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Local
public class ProvysConnectionPoolDataSource implements
        ProvysConnectionPoolDataSourceLocal, DataSource, CommonDataSource {
    private static final Logger LOG = Logger.getLogger(ProvysConnectionPoolDataSource.class.getName());

    private final PoolDataSource oraclePool;

    /**
     *
     * @throws SQLException
     */
    public ProvysConnectionPoolDataSource() throws SQLException {
        oraclePool = PoolDataSourceFactory.getPoolDataSource();
        oraclePool.setConnectionFactoryClassName(
                "oracle.jdbc.pool.OracleDataSource");
        oraclePool.setUser("KER");
        oraclePool.setPassword("khumbulasijani");
        oraclePool.setURL("jdbc:oracle:thin:@localhost:1521:PVYS");
        oraclePool.setConnectionPoolName("ProvysDB");
        oraclePool.setMinPoolSize(1);
        oraclePool.setInitialPoolSize(1);
        oraclePool.setMaxPoolSize(100);
        oraclePool.setValidateConnectionOnBorrow(true);
        // Register connection labeling callback
        ProvysConnectionLabelingCallback callBack
                = new ProvysConnectionLabelingCallback();
        oraclePool.registerConnectionLabelingCallback(callBack);
    }

    @Override
    public ProvysConnectionImpl getConnection() throws SQLException {
        return new ProvysConnectionImpl(oraclePool.getConnection());
    }

    @Override
    public ProvysConnectionImpl getConnection(String username, String password) throws SQLException {
        return new ProvysConnectionImpl(oraclePool.getConnection(username, password));
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return oraclePool.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        oraclePool.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        oraclePool.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return oraclePool.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return oraclePool.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (isWrapperFor(iface)) {
            return iface.cast(this);
        }
        return oraclePool.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return true;
        }
        return oraclePool.isWrapperFor(iface);
    }
}

/**
 * Labeling callback implementation - switch to required session
 */
class ProvysConnectionLabelingCallback implements ConnectionLabelingCallback {
    private static final Logger LOG = Logger.getLogger(ProvysConnectionLabelingCallback.class.getName());

    ProvysConnectionLabelingCallback() {
    }

    @Override
    public int cost(Properties reqLabels, Properties currentLabels) {
        // exact match
        if (reqLabels.equals(currentLabels)) {
            LOG.finest("Exact connection match (equals)");
            return 0;
        }
        // sessionlog_id matches or no-session connection
        String session1 = reqLabels.getProperty("SESSION_ID");
        if (session1 == null) {
            session1 = "FULL_READONLY";
        }
        String session2 = currentLabels.getProperty("SESSION_ID");
        if (session2 != null && session1.equals(session2)) {
            LOG.finest("Connection match using SESSION_ID");
            return 10;
        }
        // new (uninitialised) connection
        if (session2 == null) {
            LOG.finest("Uninitialized connection found");
            return 20;
        }
        // connection used for different session / context
        LOG.finest("Connection does not match supplied SESSION_ID");
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean configure(Properties reqLabels, Object conn) {
        try {
            LabelableConnection lconn = (LabelableConnection) conn;
            Properties unmatchedLabels = lconn.getUnmatchedConnectionLabels(
                    reqLabels);
            for (Map.Entry<Object, Object> label : unmatchedLabels.entrySet()) {
                String key = (String) label.getKey();
                String value = (String) label.getValue();
                if (key.equals("SESSION_ID")) {
                    if (!value.equals("FULL_READONLY")) {
                        throw new RuntimeException("Support for get connection"
                                + " with SESSION_ID not implemented yet");
                    }
                }
                lconn.applyConnectionLabel(key, value);
            }
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "SQL exception during connection configuration", e);
            return false;
        }
        return true;
    }

}
