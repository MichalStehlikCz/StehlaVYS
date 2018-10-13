/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.testrest;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stehlik
 */
@Path("")
@Stateless()
public class TestService {

    @GET
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    public TestClass getSimpleList() {
        TestClass data = new TestClass();
        data.add(1);
        data.add(2);
        return data;
    }

    @GET
    @Path("subtest")
    @Produces({MediaType.APPLICATION_JSON})
    public TestClass getSubList() {
        TestClass data = new TestClass();
        data.add(1);
        data.add(2);
        data.add(3);
        return data;
    }

    @POST
    @Path("")
    @Produces({MediaType.APPLICATION_JSON})
    public TestClass useSimpleList(TestClass data) {
        return data;
    }
}
