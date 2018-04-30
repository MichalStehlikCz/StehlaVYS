/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.datasource.ProvysConnection;
import com.provys.provysdb.datasourceimpl.ProvysConnectionPoolDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Class is REST facade on ProvysDB database.
 * Supports queries, DML and procedural calls - request is passed as SQLCall
 * or ProcCall (represented in JSON or XML), results are returned as ResultSet,
 * number of modified lines or list of output variables and their values
 * 
 * @author stehlik
 */
@ApplicationPath("")
public class ProvysDbCall {

    @Inject
    private ProvysConnectionPoolDataSource dataSource;

    @POST
    @Path("/query")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResultSet queryCall(SQLCall sqlCall) {
        ResultSet resultSet;
        try (ProvysConnection connection = dataSource.getConnection()) {
            resultSet = connection.executeQuery(sqlCall);
        } catch (SQLException e) {
            throw new ProvysSqlException(e);
        }
        return resultSet;
    }

}    
