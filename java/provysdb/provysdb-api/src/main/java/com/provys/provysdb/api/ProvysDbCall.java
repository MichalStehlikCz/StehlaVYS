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
import com.provys.provysdb.call.SqlCall;
import com.provys.provysdb.iface.ExecutorFactory;
import com.provys.provysdb.iface.JsonQueryExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
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
@Stateless()
public class ProvysDbCall {

    @Inject
    private ExecutorFactory executorFactory;
    
    @POST
    @Path("query")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JsonObject> queryCall(SqlCall sqlCall) {
        return executorFactory.getJsonQueryExecutor(sqlCall).executeQuery();
    }

    @POST
    @Path("parseAndQuery")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Map<String, Object> parseAndQueryCall(SqlCall sqlCall) {
        JsonQueryExecutor executor = executorFactory.getJsonQueryExecutor(
                sqlCall);
        executor.executeQuery();
        Map<String, Object> map = new HashMap<>(2);
        map.put("columns", executor.getColumns());
        map.put("data", executor.getData());
        return map;
    }

    @GET
    @Path("test")
    @Produces({MediaType.APPLICATION_JSON})
    public SqlCall testSerialisation() {
        List<BindValue> values = new ArrayList<>(1);
        values.add(new BindValue("domain_id", DtNameNm.of("ABC")));
        List<ColumnDef> columns = new ArrayList<>(1);
        columns.add(new ColumnDef("domain_id", DtUid.class));
        SqlCall result = new SqlCall("SELECT 1 from dual", values, columns);
        return result;
    }

    @POST
    @Path("test")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public SqlCall testDeserialization(SqlCall value) {
        return value;
    }
}    
