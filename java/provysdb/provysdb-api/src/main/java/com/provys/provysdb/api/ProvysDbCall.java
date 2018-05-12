/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.provysdb.api;

import com.provys.common.datatypes.DtNameNm;
import com.provys.common.datatypes.DtUid;
import com.provys.provysdb.call.BindValue;
import com.provys.provysdb.call.SQLCall;
import com.provys.provysdb.sqlexecutor.JsonQueryExecutorLocal;
import java.util.List;
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
    private JsonQueryExecutorLocal queryExecutor;
    
    @POST
    @Path("/query")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<JsonObject> queryCall(SQLCall sqlCall) {
        return queryExecutor.executeQuery(sqlCall);
    }

    @GET
    @Path("/test")
    @Produces({MediaType.APPLICATION_JSON})
    public BindValue testSerialisation() {
//        SQLCall sqlCall = new SQLCall();
//        sqlCall.setSql("SELECT 1 FROM dual");
//        sqlCall.addValue(new BindValue("domain_id", new DtUid("123456")));
//        return sqlCall;
        return new BindValue("domain_id", new DtNameNm("ABC"));
    }

    @POST
    @Path("/test")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public BindValue testDeserialization(BindValue value) {
        return value;
    }
}    
