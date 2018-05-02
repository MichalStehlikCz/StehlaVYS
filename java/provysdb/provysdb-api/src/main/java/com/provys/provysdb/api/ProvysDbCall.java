/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import com.provys.common.datatypes.Dt;
import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtString;
import com.provys.common.datatypes.DtVarchar;
import com.provys.common.error.ProvysSqlException;
import com.provys.provysdb.call.BindValue;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.datasource.ProvysConnection;
import com.provys.provysdb.datasource.ProvysConnectionPoolDataSourceLocal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("")
@Stateless()
public class ProvysDbCall {

    @Inject
    private ProvysConnectionPoolDataSourceLocal dataSource;

    @POST
    @Path("/query")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResultSet queryCall(SQLCall sqlCall) {
        ResultSet resultSet;
        try (ProvysConnection connection = dataSource.getConnection()) {
            resultSet = connection.executeQuery(sqlCall);
        } catch (SQLException e) {
            throw new ProvysSqlException(e);
        }
        return resultSet;
    }

    @GET
    @Path("/test")
    @Produces({MediaType.TEXT_PLAIN})
    public String testHello() {
        return "Hello";
    }

    @GET
    @Path("/testsql")
    @Produces({MediaType.APPLICATION_JSON})
    public String testSerialisation() {
        SQLCall sqlCall = new SQLCall();
        sqlCall.setSql("SELECT 1 FROM dual");
        sqlCall.addValue(new BindValue("test", new DtVarchar("value")));
        BindValue bindValue = new BindValue("test", new DtVarchar("value"));
        DtVarchar value = new DtVarchar("value");
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(bindValue);
        return result;
    }
}    
