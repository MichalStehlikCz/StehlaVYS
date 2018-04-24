/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.datasource;

import java.sql.SQLException;
import javax.ejb.Local;

/**
 * Local interface to Provys connection pool.
 * Can be used as DataSource to retrieve connections to PROVYS database.
 * Supports setting proper context (SESSION_LOG, USER_ID) to connection on
 * retrieval, optimizes reuse of already initialized connections
 * @author stehlik
 */
@Local
public interface ProvysConnectionPoolDataSourceLocal {

    /**
     * Method used to access connection to PROVYS database.
     * Parameter-less method should only be used by KERnel modules that access
     * database with full privileges, without setting user context
     * @return Initialised connection to PROVYS database
     * @throws java.sql.SQLException SQL exception raised by Oracle DataSource
     * trying to access PROVYS database
     */
    ProvysConnection getConnection() throws SQLException;

}
