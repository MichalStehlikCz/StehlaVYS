package com.provys.provysdb;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Class supports connection to Provys database and gives access to this database either via datasource or via JOOQ
 * context.
 *
 * @author stehlik
 */
@ApplicationScoped
public class ProvysDBContext {
    private final ProvysConnectionPoolDataSource provysDataSource;
    private final Configuration jooqConfiguration;

    /**
     * Default creator for Provys database context.
     * Initializes provys database datasource and builds default JOOQ configuration, that will be used when accessing
     * this database
     *
     * @throws SQLException
     */
    public ProvysDBContext() throws SQLException {
        provysDataSource = buildProvysDBDataSource();
        jooqConfiguration = buildJooqConfiguration();
    }

    private ProvysConnectionPoolDataSource buildProvysDBDataSource() throws SQLException {
        return new ProvysConnectionPoolDataSource();
    }

    private Configuration buildJooqConfiguration() {
        return new DefaultConfiguration()
                .set(provysDataSource)
                .set(SQLDialect.ORACLE12C);
    }

    /*
    Connection creation if we decide to use net.ttddyy.datasource-proxy library
    (e.g. in case monitoring capabilities of JOOQ will not be sufficient)
    private static DataSource buildProxyDataSource() {
        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
        loggingListener.setQueryLogEntryCreator(new InlineQueryLogEntryCreator());
        return ProxyDataSourceBuilder
                .create(getProvysDBDataSource())
                .name("ProvysDBProxy")
                .listener(loggingListener)
                .build();
    }
    */

    /**
     * Retrieve DSLContext for use by JOOQ to construct and execute queries and statements against Provys database.
     * Context is attached to PROVYS database and has proper logging and other settings.
     *
     * @return context based on provys data source
     */
    public DSLContext createDSL() {
        return DSL.using(jooqConfiguration);
    }

    /**
     * @return username used to open connection to Provys database
     */
    public String getUser() {
        return provysDataSource.getUser();
    }

    /**
     * @return URL of database provys datasource is connected to
     */
    public String getUrl() {
        return provysDataSource.getUrl();
    }
}
