/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import com.provys.provysdb.call.BindValue;
import com.provys.provysdb.call.ColumnDef;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.iface.ExecutorFactory;
import com.provys.provysdb.iface.JsonQueryExecutor;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
@RequestScoped()
public class ProvysDbCall {

    @Inject
    private ExecutorFactory executorFactory;
    
    @POST
    @Path("/query")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JsonObject> queryCall(SQLCall sqlCall) {
        return executorFactory.getJsonQueryExecutor(sqlCall).executeQuery();
    }

    @POST
    @Path("/parseAndQuery")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject parseAndQueryCall(SQLCall sqlCall) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Jsonb jsonb = JsonbBuilder.create();
        JsonQueryExecutor executor = executorFactory.getJsonQueryExecutor(
                sqlCall);
        executor.executeQuery();
        builder.add("columns", jsonb.toJson(executor.getColumns()));
        builder.add("data", jsonb.toJson(executor.getData()));
        return builder.build();
    }

    @GET
    @Path("/test")
    @Produces({MediaType.APPLICATION_JSON})
    public ColumnDef testSerialisation() {
/**        SQLCall result = new SQLCall();
        result.setSql("SELECT 1 from dual");
        result.addValue(new BindValue("domain_id", new DtNameNm("ABC")));
        result.addColumn(1, new ColumnDef(DtUid.class));*/
        ColumnDef result = new ColumnDef(DtUid.class);
        return result;
    }

    @POST
    @Path("/test")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public SQLCall testDeserialization(SQLCall value) {
        return value;
    }
}    
