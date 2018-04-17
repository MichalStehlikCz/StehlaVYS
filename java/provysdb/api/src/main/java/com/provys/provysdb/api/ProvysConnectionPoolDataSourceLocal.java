/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import java.sql.SQLException;
import javax.ejb.Local;

/**
 *
 * @author stehlik
 */
@Local
public interface ProvysConnectionPoolDataSourceLocal {

    ProvysConnection getConnection() throws SQLException;
    
}
