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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
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
    public Map<String, Object> parseAndQueryCall(SQLCall sqlCall) {
        JsonQueryExecutor executor = executorFactory.getJsonQueryExecutor(
                sqlCall);
        executor.executeQuery();
        Map<String, Object> map = new HashMap<>(2);
        map.put("columns", executor.getColumns());
        map.put("data", executor.getData());
        return map;
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
